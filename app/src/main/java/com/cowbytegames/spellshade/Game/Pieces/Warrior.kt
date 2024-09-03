package com.cowbytegames.spellshade.Game.Pieces

import com.cowbytegames.spellshade.Game.Board
import com.cowbytegames.spellshade.Game.Pieces.Common.Piece

class Warrior(
    override var currPos: Pair<Int,Int>, override var player: Int
) : Piece  {

    override var pieceName: String = "Warrior"

    override var maxHealth: Int = 10
    override var health: Int = 10
    override var shield: Int = 0
    override var baseDamage: Int = 2
    override var damage: Int = 2
    override var heal: Int = 0
    override var moveCost: Int = 2
    override var attackCost: Int = 2

    override var isStunned: Boolean = false
    override var stunnedDuration: Int = 0

    override var isMovePhase: Boolean = true

    var isEvolved = false

    override fun move(position: Pair<Int, Int>, board: Board) {
        super.move(position, board)

        board.useActionPoints(moveCost)
        evolveIfPossible()
    }

    override fun availableMoves(board: Board): ArrayList<Pair<Int, Int>> {
        val squares : ArrayList<Pair<Int, Int>> = arrayListOf()

        if (!isTurn(board) || !isMovePhase || board.getActionPoints() < 2 || isStunned) {
            return squares
        }

        val directions: List<Pair<Int,Int>>

        if (!isEvolved) {
            if (player == 1) {
                directions = listOf(
                    Pair(1, 0),
                    Pair(1, 1),
                    Pair(1, -1)
                )
            } else {
                directions = listOf(
                    Pair(-1, 0),
                    Pair(-1, 1),
                    Pair(-1, -1)
                )
            }
        } else {
            directions = listOf(
                Pair(1, -1), Pair(1, 1), Pair(-1, 1), Pair(-1, -1),
                Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1)
            )
        }

        for ((rowOffset, colOffset) in directions) {
            val newRow = currPos.first + rowOffset
            val newCol = currPos.second + colOffset

            if ((newRow < 7 && newCol < 7) && (newRow >= 0 && newCol >= 0)) {
                if (board.get(newRow, newCol) == null) {
                    squares.add(Pair(newRow, newCol))
                }
            }
        }

        return squares
    }

    override fun attack(position: Pair<Int, Int>, board: Board) {
        if (!isEvolved) {
            super.attack(position, board)
        } else {
            val rowOffset = position.first - currPos.first
            val colOffset = position.second - currPos.second

            val attackPatterns = listOf(
                listOf(Pair(0, -1), Pair(0, -2)),
                listOf(Pair(0, 1), Pair(0, 2)),
                listOf(Pair(1, 0), Pair(2, 0)),
                listOf(Pair(-1, 0), Pair(-2, 0))
            )

            for (attackPattern in attackPatterns) {
                if (Pair(rowOffset, colOffset) in attackPattern) {
                    aoeAttack(attackPattern, board)
                    break
                }
            }
            board.renderPieces()
            board.useActionPoints(attackCost)
        }
    }

    override fun availableAttacks(board: Board): ArrayList<Pair<Int, Int>> {
        val squares : ArrayList<Pair<Int, Int>> = arrayListOf()

        if (!isTurn(board) || isMovePhase || board.getActionPoints() < attackCost || isStunned) {
            return squares
        }

        if (!isEvolved) {
            val newRow = if (player == 1) currPos.first + 1 else currPos.first - 1

            if ((newRow < 7) && (newRow >= 0)) {
                val piece = board.get(newRow, currPos.second)
                if (piece != null && piece.player != this.player) {
                    squares.add(Pair(newRow, currPos.second))
                }
            }
        } else {
            val directions = listOf(
                Pair(0, -1), Pair(0, -2), Pair(0, 1), Pair(0, 2),
                Pair(1, 0), Pair(2, 0), Pair(-1, 0), Pair(-2, 0)
            )

            for ((rowOffset, colOffset) in directions) {
                val newRow = currPos.first + rowOffset
                val newCol = currPos.second + colOffset

                if ((newRow < 7 && newCol < 7) && (newRow >= 0 && newCol >= 0)) {
                    val piece = board.get(newRow, newCol)
                    if (piece != null && piece.player != this.player) {
                        squares.add(Pair(newRow, newCol))
                    }
                }
            }
        }

        return squares
    }

    override fun shield(position: Pair<Int, Int>) {
        TODO("Not yet implemented")
    }

    override fun passive(board: Board) {

    }

    private fun evolveIfPossible() {
        if (!isEvolved &&
            ((player == 1 && currPos.first == 6) ||
            (player == 2 && currPos.first == 0)))
        {
            isEvolved = true
            pieceName = "Berserker"
            baseDamage += 2
            damage += 2
        }
    }

    private fun aoeAttack(direction: List<Pair<Int, Int>>, board: Board) {
        for ((r, c) in direction) {
            val newRow = currPos.first + r
            val newCol = currPos.second + c
            if ((newRow < 7 && newCol < 7) && (newRow >= 0 && newCol >= 0)) {
                val piece = board.get(newRow, newCol)
                if (piece != null && piece.player != this.player) {
                    board.get(newRow, newCol)?.takeDamage(damage, board)
                }
            }
        }
    }
}