package com.cowbytegames.spellshade.Game

import android.widget.TextView
import com.cowbytegames.spellshade.Game.Pieces.*
import kotlinx.coroutines.*

class Game(val board: Board, val narratorTextView: TextView, val actionPointTextView: TextView, private val onEndTurnComplete: () -> Unit,){

    init {
        executeNonDamagePassives()
    }

    fun endTurn() {
        GlobalScope.launch(Dispatchers.Main) {
            board.resetActionPoints()
            resetIsMovePhase()

            executeDamagePassives()
            removeDeadPieces()
            board.renderPieces()
            delay(1000)
            resetBuffs()
            executeNonDamagePassives()
            delay(3000)
            resetDebuffs()
            board.renderPieces()

            board.flipActivePlayer()
            actionPointTextView.text = "Action Points: ${board.getActionPoints()}"
            onEndTurnComplete()
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

    private fun resetIsMovePhase() {
        for (i in 0 until 7) {
            for (j in 0 until 7) {
                val piece = board.get(i, j)
                if (piece != null) {
                    piece.isMovePhase = true
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