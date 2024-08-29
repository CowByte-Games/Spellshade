package com.cowbytegames.spellshade.Game

import com.cowbytegames.spellshade.Game.Pieces.*
import kotlinx.coroutines.*

class Game(val board: Board){
    fun endTurn() {
        GlobalScope.launch(Dispatchers.Main) {
            executeDamagePassives()
            removeDeadPieces()
            delay(1000)
            resetBuffs()
            executeNonDamagePassives()
            delay(1000)
            resetDebuffs()

            board.flipActivePlayer()
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
            executeHealerPassive()
            delay(1000)
            executeTankPassive()
            delay(1000)
            executeCommanderPassive()
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

    private fun removeDeadPieces() {
        for (i in 0 until 7) {
            for (j in 0 until 7) {
                val piece = board.get(i, j)
                if (piece?.health == 0) {
                    board.set(i,j,null)
                }
            }
        }
    }
}