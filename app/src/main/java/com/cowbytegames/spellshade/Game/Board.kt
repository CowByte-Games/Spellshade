package com.cowbytegames.spellshade.Game

import com.cowbytegames.spellshade.Game.Pieces.Common.Piece
import com.cowbytegames.spellshade.Game.Pieces.Warrior

class Board {
    val board: Array<Array<Piece?>> =  Array(7) { arrayOfNulls<Piece>(7) }

    init {
        board[1][1] = Warrior(arrayOf(1,1), 1)
        board[1][3] = Warrior(arrayOf(1,3), 1)
        board[1][5] = Warrior(arrayOf(1,5), 1)

        board[5][1] = Warrior(arrayOf(5,1), 2)
        board[5][3] = Warrior(arrayOf(5,3), 2)
        board[5][5] = Warrior(arrayOf(5,5), 2)
    }

    fun get(row: Int, col: Int): Piece? {
        return board.getOrNull(row)?.getOrNull(col)
    }
}