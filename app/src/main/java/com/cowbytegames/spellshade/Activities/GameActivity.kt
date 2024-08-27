package com.cowbytegames.spellshade.Activities

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.gridlayout.widget.GridLayout
import com.cowbytegames.spellshade.R

class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        renderLayout()

        val coordinateTextView = findViewById<TextView>(R.id.textView2)

        val imageViews: Array<ImageView> = arrayOf(
            findViewById<ImageView>(R.id.square_00),
            findViewById<ImageView>(R.id.square_01),
            findViewById<ImageView>(R.id.square_02),
            findViewById<ImageView>(R.id.square_03),
            findViewById<ImageView>(R.id.square_04),
            findViewById<ImageView>(R.id.square_05),
            findViewById<ImageView>(R.id.square_06),

            findViewById<ImageView>(R.id.square_10),
            findViewById<ImageView>(R.id.square_11),
            findViewById<ImageView>(R.id.square_12),
            findViewById<ImageView>(R.id.square_13),
            findViewById<ImageView>(R.id.square_14),
            findViewById<ImageView>(R.id.square_15),
            findViewById<ImageView>(R.id.square_16),

            findViewById<ImageView>(R.id.square_20),
            findViewById<ImageView>(R.id.square_21),
            findViewById<ImageView>(R.id.square_22),
            findViewById<ImageView>(R.id.square_23),
            findViewById<ImageView>(R.id.square_24),
            findViewById<ImageView>(R.id.square_25),
            findViewById<ImageView>(R.id.square_26),

            findViewById<ImageView>(R.id.square_30),
            findViewById<ImageView>(R.id.square_31),
            findViewById<ImageView>(R.id.square_32),
            findViewById<ImageView>(R.id.square_33),
            findViewById<ImageView>(R.id.square_34),
            findViewById<ImageView>(R.id.square_35),
            findViewById<ImageView>(R.id.square_36),

            findViewById<ImageView>(R.id.square_40),
            findViewById<ImageView>(R.id.square_41),
            findViewById<ImageView>(R.id.square_42),
            findViewById<ImageView>(R.id.square_43),
            findViewById<ImageView>(R.id.square_44),
            findViewById<ImageView>(R.id.square_45),
            findViewById<ImageView>(R.id.square_46),

            findViewById<ImageView>(R.id.square_50),
            findViewById<ImageView>(R.id.square_51),
            findViewById<ImageView>(R.id.square_52),
            findViewById<ImageView>(R.id.square_53),
            findViewById<ImageView>(R.id.square_54),
            findViewById<ImageView>(R.id.square_55),
            findViewById<ImageView>(R.id.square_56),

            findViewById<ImageView>(R.id.square_60),
            findViewById<ImageView>(R.id.square_61),
            findViewById<ImageView>(R.id.square_62),
            findViewById<ImageView>(R.id.square_63),
            findViewById<ImageView>(R.id.square_64),
            findViewById<ImageView>(R.id.square_65),
            findViewById<ImageView>(R.id.square_66)
        )

        for ((index, imageView) in imageViews.withIndex()) {
            val row = index / 7
            val column = index % 7
            imageView.setOnClickListener {
                val coordinates = "Row: $row, Column: $column"
                coordinateTextView.text = coordinates
            }
        }
    }

    private fun renderLayout() {
        setContentView(R.layout.activity_game)
        renderBoard()
        renderPieces()
    }

    private fun renderBoard() {
        val boardView = findViewById<View>(R.id.board)
        val rootView = findViewById<ViewGroup>(R.id.root_view)

        rootView.post {
            val marginInDp = 5
            val marginInPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, marginInDp.toFloat(), resources.displayMetrics
            ).toInt()

            val width = rootView.width - 2 * marginInPx
            val height = rootView.height - 2 * marginInPx
            val size = minOf(width, height)

            val layoutParams = boardView.layoutParams
            layoutParams.width = size
            layoutParams.height = size
            boardView.layoutParams = layoutParams

            val constraintLayout = rootView as ConstraintLayout
            val constraintSet = ConstraintSet()
            constraintSet.clone(constraintLayout)

            constraintSet.clear(boardView.id, ConstraintSet.TOP)
            constraintSet.clear(boardView.id, ConstraintSet.START)
            constraintSet.clear(boardView.id, ConstraintSet.END)
            constraintSet.clear(boardView.id, ConstraintSet.BOTTOM)

            constraintSet.connect(boardView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, marginInPx)
            constraintSet.connect(boardView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, marginInPx)
            constraintSet.connect(boardView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, marginInPx)

            constraintSet.applyTo(constraintLayout)
        }
    }

    private fun renderPieces() {
        val gridLayout = findViewById<GridLayout>(R.id.board_grid)

        for (i in 0 until gridLayout.childCount) {
            val imageView = gridLayout.getChildAt(i) as ImageView
//            imageView.setImageResource(R.drawable.)
        }
    }
}