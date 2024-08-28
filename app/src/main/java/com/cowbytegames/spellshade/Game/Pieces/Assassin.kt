package com.cowbytegames.spellshade.Game.Pieces

import com.cowbytegames.spellshade.Game.Board
import com.cowbytegames.spellshade.Game.Pieces.Common.Piece

class Assassin(
    override var currPos: Pair<Int,Int>, override var player: Int
) : Piece  {

    override var pieceName: String = "Assassin"

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
        TODO("Not yet implemented")
    }

    override fun availableMoves(board: Board): ArrayList<Pair<Int,Int>> {
        return arrayListOf()
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