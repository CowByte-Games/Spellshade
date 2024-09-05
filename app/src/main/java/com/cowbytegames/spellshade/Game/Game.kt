package com.cowbytegames.spellshade.Game

import android.widget.TextView
import android.widget.Toast
import com.cowbytegames.spellshade.Game.Pieces.*
import com.cowbytegames.spellshade.Game.Pieces.Common.Piece
import kotlinx.coroutines.*

class Game(val board: Board, val narratorTextView: TextView, val actionPointTextView: TextView, private val doneProcessingCallBack: () -> Unit,){

    init {
        executeHealerPassive()
        executeTankPassive()
        executeCommanderPassive()
        doneProcessingCallBack()
    }

    fun endTurn() {
        GlobalScope.launch(Dispatchers.Main) {
            board.resetActionPoints()
            resetActionFlags()

            executeDamagePassives()
            removeDeadPieces()
            board.renderPieces()
            delay(1000)
            resetBuffs()
            executeNonDamagePassives()
            delay(3000)
            resetDebuffs()
            board.renderPieces()
            delay(1000)
            isEndGame()

            board.flipActivePlayer()
            actionPointTextView.text = "Action Points: ${board.getActionPoints()}"
            doneProcessingCallBack()
        }
    }

    private fun isEndGame() {
        val attackers = listOf(Wizard::class, Warrior::class, Archer::class, Assassin::class)

        var blueAttackers = 0
        var redAttackers = 0
        var blueCommander = false
        var redCommander = false

        var piece : Piece? = null

        for (i in 0 until 7) {
            for (j in 0 until 7) {
                piece = board.get(i, j)
                if (piece == null) {
                    continue
                }
                if (piece::class in attackers) {
                    if (piece.player == 1) {
                        blueAttackers += 1
                    } else {
                        redAttackers += 1
                    }
                }
                else if (piece is Commander) {
                    if (piece.player == 1) {
                        blueCommander = true
                    } else {
                        redCommander = true
                    }
                }
            }
        }

        if (blueCommander && !redCommander) {
            narratorTextView.text = "BLUE TEAM WINS"
        } else if (!blueCommander && redCommander) {
            narratorTextView.text = "RED TEAM WINS"
        } else if (!blueCommander && !redCommander) {
            narratorTextView.text = "DRAW"
        } else if (blueAttackers > 0 && redAttackers == 0) {
            narratorTextView.text = "BLUE TEAM WINS"
        } else if (blueAttackers == 0 && redAttackers > 0) {
            narratorTextView.text = "RED TEAM WINS"
        } else if (blueAttackers == 0 && redAttackers == 0) {
            narratorTextView.text = "STALEM8"
        }
    }

    private fun resetBuffs() {
        for (i in 0 until 7) {
            for (j in 0 until 7) {
                val piece = board.get(i, j)
                if (piece != null) {
                    piece.damage = piece.baseDamage
                    piece.shield = 0
                }
            }
        }
    }

    private fun resetDebuffs() {
        for (i in 0 until 7) {
            for (j in 0 until 7) {
                val piece = board.get(i, j)
                if (piece != null) {
                    piece.stunnedDuration -= 1
                    if (piece.stunnedDuration <= 0) {
                        piece.stunnedDuration = 0
                        piece.isStunned = false
                    }
                }
            }
        }
    }

    private fun executeDamagePassives() {
        narratorTextView.text = "Executing damage passives"
        for (i in 0 until 7) {
            for (j in 0 until 7) {
                val piece = board.get(i, j)
                if (piece is Archer) {
                    piece.passive(board)
                }
            }
        }
    }

    private fun executeNonDamagePassives() {
        GlobalScope.launch(Dispatchers.Main) {
            narratorTextView.text = "Healers healing allies"
            executeHealerPassive()
            delay(1000)
            narratorTextView.text = "Tanks shielding allies"
            executeTankPassive()
            delay(1000)
            narratorTextView.text = "Commander buffing allies"
            executeCommanderPassive()
            delay(1000)
            narratorTextView.text = ""
        }
    }

    private fun executeHealerPassive() {
        for (i in 0 until 7) {
            for (j in 0 until 7) {
                when (val piece = board.get(i, j)) {
                    is Healer -> piece.passive(board)
                }
            }
        }
    }

    private fun executeTankPassive() {
        for (i in 0 until 7) {
            for (j in 0 until 7) {
                when (val piece = board.get(i, j)) {
                    is Tank -> piece.passive(board)
                }
            }
        }
    }

    private fun executeCommanderPassive() {
        for (i in 0 until 7) {
            for (j in 0 until 7) {
                when (val piece = board.get(i, j)) {
                    is Commander -> piece.passive(board)
                }
            }
        }
    }

    private fun resetActionFlags() {
        for (i in 0 until 7) {
            for (j in 0 until 7) {
                val piece = board.get(i, j)
                if (piece != null) {
                    piece.isMovePhase = true
                    piece.isAttackPhase = false
                }
            }
        }
    }

    private fun removeDeadPieces() {
        for (i in 0 until 7) {
            for (j in 0 until 7) {
                val piece = board.get(i, j)
                if (piece != null && piece.health <= 0) {
                    board.set(i,j,null)
                }
            }
        }
    }
}