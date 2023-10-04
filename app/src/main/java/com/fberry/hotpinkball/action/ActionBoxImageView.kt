package com.fberry.hotpinkball.action

import android.content.Context
import android.util.AttributeSet
import android.widget.GridLayout
import android.widget.ImageView
import com.fberry.hotpinkball.R

class ActionBoxImageView(context: Context, attrs: AttributeSet) : GridLayout(context, attrs) {

    private val board = Board(15, 13)
    private var ballImage: ImageView

    var onBallInBasketListener: ((basketIndex: Int) -> Unit)? = null

    init {
        rowCount = 15
        columnCount = 13

        for (i in 0 until rowCount) {
            for (j in 0 until columnCount) {
                val imageView = ImageView(context)
                if (board.pins.any { it.x == j && it.y == i }) {
                    imageView.setImageResource(R.drawable.obstacle)
                } else if (board.baskets.any { it.x == j && it.y == i }) {
                    when (j) {
                        0, 12 -> imageView.setImageResource(R.drawable.basket3)
                        2, 10 -> imageView.setImageResource(R.drawable.basket2)
                        6 -> imageView.setImageResource(R.drawable.basket0)
                        else -> imageView.setImageResource(R.drawable.basket1)
                    }
                } else {
                    imageView.setImageResource(android.R.color.transparent)
                }

                val params = LayoutParams()
                params.width = 0
                params.height = 0
                params.rowSpec = spec(i, 1f)
                params.columnSpec = spec(j, 1f)
                imageView.layoutParams = params

                addView(imageView)
            }
        }

        ballImage = ImageView(context).apply {
            setImageResource(R.drawable.ball)
        }
        val ballParams = LayoutParams()
        ballParams.width = 0
        ballParams.height = 0
        ballParams.rowSpec = spec(0, 1f)
        ballParams.columnSpec = spec(6, 1f)
        ballImage.layoutParams = ballParams
        addView(ballImage)
    }

    fun moveBall() {
        val currentPos = Point(
            ballImage.x.toInt() / (width / columnCount),
            ballImage.y.toInt() / (height / rowCount)
        )
        val nextMove = board.getNextMove(currentPos)
        ballImage.x = nextMove.x * (width / columnCount).toFloat()
        ballImage.y = nextMove.y * (height / rowCount).toFloat()

        if (nextMove.y == 14 && board.baskets.any { it.x == nextMove.x }) {
            val basketIndex = board.baskets.indexOfFirst { it.x == nextMove.x }
            onBallInBasketListener?.invoke(basketIndex)
        } else {
            postDelayed({ moveBall() }, 200)
        }
    }

    fun resetBallPosition() {
        ballImage.x = 6 * (width / columnCount).toFloat()
        ballImage.y = 0 * (height / rowCount).toFloat()
    }
}
