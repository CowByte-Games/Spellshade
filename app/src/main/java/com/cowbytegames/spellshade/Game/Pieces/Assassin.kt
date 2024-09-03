package com.cowbytegames.spellshade.Game.Pieces

import com.cowbytegames.spellshade.Game.Board
import com.cowbytegames.spellshade.Game.Pieces.Common.Piece

class Assassin(
    override var currPos: Pair<Int,Int>, override var player: Int
) : Piece  {

    override var pieceName: String = "Assassin"

    override var maxHealth: Int = 10
    override var health: Int = 10
    override var shield: Int = 0
    override var baseDamage: Int = 2
    override var damage: Int = 2
    override var moveCost: Int = 1
    override var attackCost: Int = 1

    override var isStunned: Boolean = false
    override var stunnedDuration: Int = 0

    override var isMovePhase: Boolean = true

    override fun move(position: Pair<Int, Int>, board: Board) {
        val rowOffset = position.first - currPos.first
        val colOffset = position.second - currPos.second
        if (isAnAttack(position, board)) {
            attack(Pair(currPos.first + rowOffset/2, currPos.second + colOffset/2), board)
        }

        super.move(position, board)

        board.useActionPoints(moveCost)
        isMovePhase = true
    }

    override fun availableMoves(board: Board): ArrayList<Pair<Int,Int>> {
        val squares : ArrayList<Pair<Int, Int>> = arrayListOf()

        if (!isTurn(board) || board.getActionPoints() < moveCost || isStunned) {
            return squares
        }

        val directions = listOf(
            Pair(1, -1), Pair(1, 1), Pair(-1, 1), Pair(-1, -1),
            Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1),
            Pair(2, 2), Pair(2, 0), Pair(2, -2),
            Pair(0, 2), Pair(0, -2),
            Pair(-2, 2), Pair(-2, 0), Pair(-2, -2)
        )

        for ((rowOffset, colOffset) in directions) {
            val newRow = currPos.first + rowOffset
            val newCol = currPos.second + colOffset

            if ((newRow < 7 && newCol < 7) && (newRow >= 0 && newCol >= 0)) {
                if (board.get(newRow, newCol) == null) {
                    if (!isAnAttack(Pair(newRow, newCol), board) || board.getActionPoints() >= moveCost + attackCost ) {
                        squares.add(Pair(newRow, newCol))
                    }
                }
            }
        }

        return squares
    }

    override fun availableAttacks(board: Board): ArrayList<Pair<Int, Int>> {
        return arrayListOf()
    }

    override fun shield(position: Pair<Int, Int>) {
        TODO("Not yet implemented")
    }

    override fun passive(board: Board) {

    }

    private fun isAnAttack(position: Pair<Int, Int>, board: Board): Boolean {
        val rowOffset = position.first - currPos.first
        val colOffset = position.second - currPos.second

        if (Pair(rowOffset, colOffset) in listOf(
                Pair(2, 2), Pair(2, 0), Pair(2, -2),
                Pair(0, 2), Pair(0, -2),
                Pair(-2, 2), Pair(-2, 0), Pair(-2, -2))
        ) {
            val attackedPiece = board.get(currPos.first + rowOffset/2, currPos.second + colOffset/2)
            if (attackedPiece != null && attackedPiece.player != this.player) {
                return true
            }
        }
        return false
    }
}