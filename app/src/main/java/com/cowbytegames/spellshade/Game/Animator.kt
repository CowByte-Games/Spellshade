package com.cowbytegames.spellshade.Game

import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.cowbytegames.spellshade.Activities.GameActivity
import com.cowbytegames.spellshade.R

class Animator(val imageViews: Array<ImageView>, val gameActivity: GameActivity) {
    fun animateHeal(row: Int, col: Int) {
        val index = row * 7 + col

        val rootView = gameActivity.findViewById<ViewGroup>(R.id.root_view)

        var animation: AnimationDrawable

        val imageView = ImageView(gameActivity).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            id = View.generateViewId()
            setBackgroundResource(R.drawable.animation)
            scaleType = ImageView.ScaleType.CENTER_CROP

            animation = background as AnimationDrawable
        }

        rootView.addView(imageView)
        animation.start()
    }
}