package com.cowbytegames.spellshade.Game.Pieces.Common

import com.cowbytegames.spellshade.Game.Board

interface Piece {
    var player: Int
    var pieceName: String

    var maxHealth: Int
    var health: Int
    var shieldStrength: Int
    var shield: Int
    var baseDamage: Int
    var damage: Int
    var heal: Int

    var isStunned: Boolean
    var stunnedDuration: Int

    var currPos: Pair<Int,Int>

    fun move(position: Pair<Int, Int>, board: Board)
    fun availableMoves(board: Board): ArrayList<Pair<Int, Int>>
    fun attack(position: Pair<Int, Int>)
    fun heal(position: Pair<Int, Int>)
    fun shield(position: Pair<Int, Int>)
    fun stun(position: Pair<Int, Int>)
    fun passive(board: Board)
}