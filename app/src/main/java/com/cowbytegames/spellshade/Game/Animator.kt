package com.cowbytegames.spellshade.Game

import android.graphics.drawable.AnimationDrawable
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.cowbytegames.spellshade.Activities.GameActivity
import com.cowbytegames.spellshade.R

class Animator(val gameActivity: GameActivity, val debugTextView: TextView) {

    fun animateHeal(row: Int, col: Int) {
        val rootView = gameActivity.findViewById<ViewGroup>(R.id.root_view) as ConstraintLayout

        rootView.post {
            // Calculate margins in px
            val marginInDp = 5
            val marginInPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, marginInDp.toFloat(), gameActivity.resources.displayMetrics
            ).toInt()

            // Get the size of the board
            val width = rootView.width - 2 * marginInPx
            val height = rootView.height - 2 * marginInPx
            val boardSize = minOf(width, height)

            // Calculate the size of each cell (since it's a 7x7 grid)
            val cellSize = boardSize / 7

            // Calculate the center of the target cell
            val cellCenterX = marginInPx + (col * cellSize) + (cellSize / 2)
            val cellCenterY = marginInPx + (row * cellSize) + (cellSize / 2)

            // Calculate the size of the ImageView (3 times the cell size)
            val imageViewSize = cellSize * 3

            // Create a new ImageView dynamically
            val imageView = ImageView(gameActivity).apply {
                id = View.generateViewId()
                setBackgroundResource(R.drawable.animation_heal_aoe)
                scaleType = ImageView.ScaleType.CENTER_CROP
                layoutParams = ViewGroup.LayoutParams(imageViewSize, imageViewSize)
            }

            // Add the ImageView to the root view
            rootView.addView(imageView)

            // Set absolute X and Y positions
            imageView.x = (cellCenterX - imageViewSize / 2).toFloat()
            imageView.y = (cellCenterY - imageViewSize / 2).toFloat()

            // Start the animation
            val animation = imageView.background as AnimationDrawable
            imageView.post {
                animation.start()
            }

            // Remove the ImageView after 1 second (1000 milliseconds)
            Handler(Looper.getMainLooper()).postDelayed({
                animation.stop()
                imageView.background = null
                rootView.removeView(imageView)
            }, 250)
        }
    }
}