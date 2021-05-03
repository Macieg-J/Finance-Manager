package com.example.financemanager

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class ChartView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val setOfEntryPoints = mutableSetOf<EntryPoint>()
    private val chartStartPointOX = 100f
    private val chartStartPointOY = 1700f

    private val paint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 30f
        style = Paint.Style.STROKE
        textSize = 100f
    }

    private val paintLineColor1 = Paint().apply {
        color = Color.GREEN
        strokeWidth = 10f
        isAntiAlias = true
    }

    // lista punktów (może Set) połączona Path
    fun addPoint(x: Int, y: Int) {
        invalidate()
    }

    fun setData(entryPoints: List<EntryPoint>) {
        setOfEntryPoints.clear()
        setOfEntryPoints.addAll(entryPoints)
//        xMin = entryPoints.minByOrNull { it.xPosition }?.xPosition ?: 0
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        with(canvas) {
            drawLine(
                chartStartPointOX,
                chartStartPointOY,
                1000f,
                chartStartPointOY,
                paint
            ) // OX axis
            drawLine(chartStartPointOX, chartStartPointOY, chartStartPointOX, 80f, paint) // OY axis
            drawText("Time", 140f, 1800f, paint) // OX title
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

}