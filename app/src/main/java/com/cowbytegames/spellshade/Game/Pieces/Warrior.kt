package com.cowbytegames.spellshade.Game.Pieces

import com.cowbytegames.spellshade.Game.Board
import com.cowbytegames.spellshade.Game.Pieces.Common.Piece

class Warrior(
    override var currPos: Pair<Int,Int>, override var player: Int
) : Piece  {

    override var pieceName: String = "Warrior"

    override var maxHealth: Int = 10
    override var health: Int = 10
    override var maxShield: Int = 2
    override var shield: Int = 0
    override var damage: Int = 2
    override var heal: Int = 0

    override var isStun: Boolean = false
    override var isBuffed: Boolean = false

    override fun move(position: Array<Int>) {

    }

    override fun availableMoves(board: Board): ArrayList<Pair<Int, Int>> {
        val squares : ArrayList<Pair<Int, Int>> = arrayListOf()

        val directions: List<Pair<Int,Int>>
        if (player == 1) {
            directions = listOf(
                Pair(1, 0),
                Pair(1, 1),
                Pair(1, -1)
            )
        }
        else {
            directions = listOf(
                Pair(-1, 0),
                Pair(-1, 1),
                Pair(-1, -1)
            )
        }

        for ((rowOffset, colOffset) in directions) {
            val newRow = currPos.first + rowOffset
            val newCol = currPos.second + colOffset

            if (board.get(newRow, newCol) == null) {
                squares.add(Pair(newRow, newCol))
            }
        }

        return squares
    }

    override fun attack(position: Array<Int>) {
        TODO("Not yet implemented")
    }

    override fun heal(position: Array<Int>) {
        TODO("Not yet implemented")
    }

    override fun shield(position: Array<Int>) {
        TODO("Not yet implemented")
    }

    override fun stun(position: Array<Int>) {
        TODO("Not yet implemented")
    }
}