package com.cowbytegames.spellshade.Game.Pieces

import com.cowbytegames.spellshade.Game.Board
import com.cowbytegames.spellshade.Game.Pieces.Common.Piece

class Witch(
    override var currPos: Pair<Int,Int>, override var player: Int
) : Piece  {

    override var pieceName: String = "Witch"

    override var maxHealth: Int = 10
    override var health: Int = 10
    override var maxShield: Int = 2
    override var shield: Int = 0
    override var damage: Int = 2
    override var heal: Int = 0

    override var isStun: Boolean = false
    override var isBuffed: Boolean = false

    override fun move(position: Array<Int>) {
        TODO("Not yet implemented")
    }

    override fun availableMoves(board: Board): ArrayList<Pair<Int,Int>> {
        TODO("Not yet implemented")
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