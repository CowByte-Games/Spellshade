package com.cowbytegames.spellshade.Game

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.LayerDrawable
import android.widget.ImageView
import androidx.gridlayout.widget.GridLayout
import com.cowbytegames.spellshade.Game.Pieces.Common.Piece
import com.cowbytegames.spellshade.Game.Pieces.*
import com.cowbytegames.spellshade.R
import kotlin.math.abs
import kotlin.math.ceil

class Board(val imageViews: Array<ImageView>, val animator: Animator) {
    val board: Array<Array<Piece?>> =  Array(7) { arrayOfNulls<Piece>(7) }
    private var activePlayer = 1
    private var actionPoints = 4

    init {
        board[1][1] = Warrior(Pair(1,1), 1)
        board[1][3] = Warrior(Pair(1,3), 1)
        board[1][5] = Warrior(Pair(1,5), 1)
        board[5][1] = Warrior(Pair(5,1), 2)
        board[5][3] = Warrior(Pair(5,3), 2)
        board[5][5] = Warrior(Pair(5,5), 2)

        board[1][2] = Witch(Pair(1,2), 1)
        board[5][4] = Witch(Pair(5,4), 2)

        board[1][4] = Wizard(Pair(1,4), 1)
        board[5][2] = Wizard(Pair(5,2), 2)

        board[0][0] = Assassin(Pair(0,0), 1)
        board[0][6] = Assassin(Pair(0,6), 1)
        board[6][0] = Assassin(Pair(6,0), 2)
        board[6][6] = Assassin(Pair(6,6), 2)

        board[0][1] = Archer(Pair(0,1), 1)
        board[0][5] = Archer(Pair(0,5), 1)
        board[6][1] = Archer(Pair(6,1), 2)
        board[6][5] = Archer(Pair(6,5), 2)

        board[1][0] = Healer(Pair(1,0), 1)
        board[1][6] = Healer(Pair(1,6), 1)
        board[5][0] = Healer(Pair(5,0), 2)
        board[5][6] = Healer(Pair(5,6), 2)

        board[0][2] = Tank(Pair(0,2), 1)
        board[0][4] = Tank(Pair(0,4), 1)
        board[6][2] = Tank(Pair(6,2), 2)
        board[6][4] = Tank(Pair(6,4), 2)

        board[0][3] = Commander(Pair(0,3), 1)
        board[6][3] = Commander(Pair(6,3), 2)
    }

    fun get(row: Int, col: Int): Piece? {
        return board.getOrNull(row)?.getOrNull(col)
    }

    fun set(row: Int, col: Int, piece: Piece?) {
        board[row][col] = piece
    }

    fun fixCellImageSize(gridLayout: GridLayout, boardView: ImageView) {
        for (i in 0 until 7) {
            for (j in 0 until 7) {
                val index = i * 7 + j

                val imageView = gridLayout.getChildAt(index) as ImageView

                val size = ceil(boardView.width / 7.0).toInt()
                val layoutParams = imageView.layoutParams
                layoutParams.width = size
                layoutParams.height = size
                imageView.layoutParams = layoutParams
            }
        }
    }

    fun renderPieces() {
        for (i in 0 until 7) {
            for (j in 0 until 7) {
                val index = i * 7 + j

                val imageView = imageViews[index]
                imageView.setImageResource(0)

                val piece = board[i][j]

                var hpBarDrawable = R.drawable.hp_0
                if (piece != null){
                    if (piece.isStunned) {
                        imageView.setBackgroundColor(Color.BLUE)
                    } else {
                        imageView.setBackgroundColor(0)
                    }
                    hpBarDrawable = getClosestHpBar(piece.health, piece.maxHealth)
                }

                if (piece is Warrior) {
                    if (piece.player == 1 && !piece.isEvolved) { updateImageView(imageView, R.drawable.blue_warrior, hpBarDrawable) }
                    else if (piece.player == 2 && !piece.isEvolved) { updateImageView(imageView, R.drawable.red_warrior, hpBarDrawable) }
                    else if (piece.player == 1 && piece.isEvolved) { updateImageView(imageView, R.drawable.blue_berseker, hpBarDrawable) }
                    else if (piece.player == 2 && piece.isEvolved) { updateImageView(imageView, R.drawable.red_berserker, hpBarDrawable) }
                }
                else if (piece is Witch) {
                    if (piece.player == 1) { updateImageView(imageView, R.drawable.blue_witch, hpBarDrawable) }
                    else { updateImageView(imageView, R.drawable.red_witch, hpBarDrawable) }
                }
                else if (piece is Wizard) {
                    if (piece.player == 1) { updateImageView(imageView, R.drawable.blue_wizard, hpBarDrawable) }
                    else { updateImageView(imageView, R.drawable.red_wizard, hpBarDrawable) }
                }
                else if (piece is Assassin) {
                    if (piece.player == 1) { updateImageView(imageView, R.drawable.blue_assassin, hpBarDrawable) }
                    else { updateImageView(imageView, R.drawable.red_assassin, hpBarDrawable) }
                }
                else if (piece is Archer) {
                    if (piece.player == 1) { updateImageView(imageView, R.drawable.blue_archer, hpBarDrawable) }
                    else { updateImageView(imageView, R.drawable.red_archer, hpBarDrawable) }
                }
                else if (piece is Healer) {
                    if (piece.player == 1) { updateImageView(imageView, R.drawable.blue_healer, hpBarDrawable) }
                    else { updateImageView(imageView, R.drawable.red_healer, hpBarDrawable) }
                }
                else if (piece is Tank) {
                    if (piece.player == 1) { updateImageView(imageView, R.drawable.blue_tank, hpBarDrawable) }
                    else { updateImageView(imageView, R.drawable.red_tank, hpBarDrawable) }
                }
                else if (piece is Commander) {
                    if (piece.player == 1) { updateImageView(imageView, R.drawable.blue_commander, hpBarDrawable) }
                    else { updateImageView(imageView, R.drawable.red_commander, hpBarDrawable) }
                }
            }
        }
    }

    fun resetAvailableAttacksAndMovesRender() {
        for (imageView in imageViews) {
            val background = imageView.background
            if (background is ColorDrawable && (background.color == Color.GREEN || background.color == Color.RED)) {
                imageView.setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }
    fun setAvailableMovesRender(cells: ArrayList<Pair<Int, Int>>) {
        for (cell in cells) {
            val (r, c) = cell
            val i = r * 7 + c
            val iV = imageViews[i]

            iV.setBackgroundColor(Color.GREEN)
        }
    }
    fun setAvailableAttacksRender(cells: ArrayList<Pair<Int, Int>>) {
        for (cell in cells) {
            val (r, c) = cell
            val i = r * 7 + c
            val iV = imageViews[i]

            iV.setBackgroundColor(Color.RED)
        }
    }

    fun getActivePlayer(): Int {
        return activePlayer
    }
    fun flipActivePlayer() {
        if (activePlayer == 1) {
            activePlayer = 2
        } else {
            activePlayer = 1
        }
    }

    fun getActionPoints(): Int {
        return actionPoints
    }
    fun useActionPoints(cost: Int) {
        actionPoints -= cost
    }
    fun resetActionPoints() {
        actionPoints = 6
    }

    fun animateHeal(row: Int, col: Int) {
        animator.animateHeal(row,col)
    }

    private fun updateImageView(imageView: ImageView, pieceDrawableRes: Int, hpDrawableRes: Int) {
        val layerDrawable: LayerDrawable?

        val defaultPieceDrawable = imageView.context.getDrawable(pieceDrawableRes)
        val defaultHpDrawable = imageView.context.getDrawable(hpDrawableRes)

        layerDrawable = LayerDrawable(arrayOf(defaultPieceDrawable, defaultHpDrawable))

        imageView.setImageDrawable(layerDrawable)
    }

    private fun getClosestHpBar(hp: Int, maxHp: Int): Int {
        val hpPercentage = (hp.toFloat() / maxHp) * 100

        val closestDrawable = hpBarDrawables.minByOrNull {
            abs(it.percentage - hpPercentage)
        }

        return closestDrawable?.drawableRes ?: R.drawable.hp_0
    }

    data class HpBarDrawable(val percentage: Int, val drawableRes: Int)
    val hpBarDrawables = listOf(
        HpBarDrawable(0, R.drawable.hp_0),
        HpBarDrawable(1, R.drawable.hp_1),
        HpBarDrawable(4, R.drawable.hp_4),
        HpBarDrawable(7, R.drawable.hp_7),
        HpBarDrawable(10, R.drawable.hp_10),
        HpBarDrawable(13, R.drawable.hp_13),
        HpBarDrawable(16, R.drawable.hp_16),
        HpBarDrawable(18, R.drawable.hp_18),
        HpBarDrawable(21, R.drawable.hp_21),
        HpBarDrawable(24, R.drawable.hp_24),
        HpBarDrawable(27, R.drawable.hp_27),
        HpBarDrawable(30, R.drawable.hp_30),
        HpBarDrawable(33, R.drawable.hp_33),
        HpBarDrawable(35, R.drawable.hp_35),
        HpBarDrawable(38, R.drawable.hp_38),
        HpBarDrawable(41, R.drawable.hp_41),
        HpBarDrawable(44, R.drawable.hp_44),
        HpBarDrawable(47, R.drawable.hp_47),
        HpBarDrawable(49, R.drawable.hp_49),
        HpBarDrawable(52, R.drawable.hp_52),
        HpBarDrawable(55, R.drawable.hp_55),
        HpBarDrawable(58, R.drawable.hp_58),
        HpBarDrawable(61, R.drawable.hp_61),
        HpBarDrawable(63, R.drawable.hp_63),
        HpBarDrawable(66, R.drawable.hp_66),
        HpBarDrawable(69, R.drawable.hp_69),
        HpBarDrawable(72, R.drawable.hp_72),
        HpBarDrawable(75, R.drawable.hp_75),
        HpBarDrawable(77, R.drawable.hp_77),
        HpBarDrawable(80, R.drawable.hp_80),
        HpBarDrawable(83, R.drawable.hp_83),
        HpBarDrawable(86, R.drawable.hp_86),
        HpBarDrawable(89, R.drawable.hp_89),
        HpBarDrawable(92, R.drawable.hp_92),
        HpBarDrawable(94, R.drawable.hp_94),
        HpBarDrawable(97, R.drawable.hp_97),
        HpBarDrawable(99, R.drawable.hp_99),
        HpBarDrawable(100, R.drawable.hp_100),
    )

}