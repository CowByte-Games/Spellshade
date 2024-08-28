package com.cowbytegames.spellshade.Game.Pieces

import android.widget.ImageView
import com.cowbytegames.spellshade.Game.Board
import com.cowbytegames.spellshade.Game.Pieces.Common.Piece
import kotlin.math.ceil

class Healer(
    override var currPos: Pair<Int,Int>, override var player: Int
) : Piece  {

    override var pieceName: String = "Healer"

    override var maxHealth: Int = 10
    override var health: Int = 10
    override var maxShield: Int = 2
    override var shield: Int = 0
    override var maxDamage: Int = 2
    override var damage: Int = 2
    override var heal: Int = 0

    override var isStun: Boolean = false
    override var isBuffed: Boolean = false

    override fun move(position: Pair<Int, Int>, board: Board) {
        board.set(currPos.first, currPos.second, null)
        currPos = position
        board.set(currPos.first, currPos.second, this)
    }

    override fun availableMoves(board: Board): ArrayList<Pair<Int,Int>> {
        val squares : ArrayList<Pair<Int, Int>> = arrayListOf()

        for (i in 0 until 7) {
            for (j in 0 until 7) {
                if (board.get(i, j) == null) {
                    squares.add(Pair(i,j))
                }
            }
        }

        return squares
    }

    override fun attack(position: Pair<Int, Int>) {
        TODO("Not yet implemented")
    }

    override fun heal(position: Pair<Int, Int>) {
        TODO("Not yet implemented")
    }

    override fun shield(position: Pair<Int, Int>) {
        TODO("Not yet implemented")
    }

    override fun stun(position: Pair<Int, Int>) {
        TODO("Not yet implemented")
    }

    override fun passive(board: Board) {

    }
}