package com.cowbytegames.spellshade.Game.Pieces.Common

import com.cowbytegames.spellshade.Game.Board

interface Piece {
    var player: Int
    var pieceName: String

    var maxHealth: Int
    var health: Int
    var maxShield: Int
    var shield: Int
    var damage: Int
    var heal: Int

    var isStun: Boolean
    var isBuffed: Boolean

    var currPos: Pair<Int,Int>

    fun move(position: Array<Int>)
    fun availableMoves(board: Board): ArrayList<Pair<Int, Int>>
    fun attack(position: Array<Int>)
    fun heal(position: Array<Int>)
    fun shield(position: Array<Int>)
    fun stun(position: Array<Int>)
}