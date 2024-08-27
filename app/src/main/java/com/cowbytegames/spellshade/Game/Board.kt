package com.cowbytegames.spellshade.Game

import android.view.View
import android.widget.ImageView
import androidx.gridlayout.widget.GridLayout
import com.cowbytegames.spellshade.Game.Pieces.Common.Piece
import com.cowbytegames.spellshade.Game.Pieces.Warrior
import com.cowbytegames.spellshade.Game.Pieces.Witch
import com.cowbytegames.spellshade.R
import kotlin.math.ceil

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

    fun renderBoard(gridLayout: GridLayout, boardView: ImageView) {
        for (i in 0 until 7) {
            for (j in 0 until 7) {
                val index = i * 7 + j

                val imageView = gridLayout.getChildAt(index) as ImageView

                val size = ceil(boardView.width/7.0).toInt()
                val layoutParams = imageView.layoutParams
                layoutParams.width = size
                layoutParams.height = size
                imageView.layoutParams = layoutParams

                val piece = board[i][j]

                if (piece is Warrior) {
                    imageView.setImageResource(R.drawable.blue_warrior)
                } else if (piece is Witch) {
                    imageView.setImageResource(R.drawable.blue_witch)
                }
            }
        }
    }
}