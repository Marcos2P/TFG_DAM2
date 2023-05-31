package com.example.tfg_dam2.clases_alternativas
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Circle(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var radius = 100f
    private var circleColor = Color.RED

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f

        val paint = Paint()
        paint.color = circleColor
        paint.style = Paint.Style.FILL

        canvas.drawCircle(centerX, centerY, radius, paint)
    }

    fun setCircleColor(color: String) {
        circleColor = if (color == "red"){
            Color.RED
        }else{
            Color.GREEN
        }
        invalidate()
    }
}