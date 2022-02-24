package com.example.customview.draw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.nio.file.attribute.AttributeView

/**
 *Author GWJ
 *2022/1/14 15:42
 **/
class CanvasDemo : View {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG) //打开抗锯齿,消除毛边现象
    private val paint1 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paint2 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paint3 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paint4 = Paint(Paint.ANTI_ALIAS_FLAG)



    constructor(context: Context):super(context)
    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet)


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.BLUE
        paint1.color = Color.RED
        paint2.color = Color.GREEN
        paint3.color = Color.BLACK
        paint4.color = Color.CYAN

        paint.style = Paint.Style.STROKE
        paint1.style = Paint.Style.STROKE
        paint4.style = Paint.Style.FILL

        paint.strokeWidth = 5F
        paint1.strokeWidth = 5F
        paint3.strokeWidth = 10f

        paint3.strokeCap = Paint.Cap.ROUND

        //圆形 drawCircle()
        canvas?.drawCircle(300f,300f,100f,paint)
        canvas?.drawCircle(400f,400f,100f,paint1)
        canvas?.drawCircle(500f,300f,100f,paint2)
        canvas?.drawCircle(600f,400f,100f,paint1)
        canvas?.drawCircle(700f,300f,100f,paint)

        //矩形 drawRect()
        canvas?.drawRect(400f,50f,500f,150f,paint2)
        canvas?.drawRect(200f,200f,800f,500f,paint1)
        //圆角矩形 drawRoundRect()
        canvas?.drawRoundRect(530f,50f,830f,150f,30f,30f,paint2)
        canvas?.drawRoundRect(850f,50f,1150f,150f,30f,30f,paint1)

        //点 drawPoint()
        canvas?.drawPoint(300f,300f,paint3)
        canvas?.drawPoint(600f,400f,paint3)

        //椭圆 drawOval()
        canvas?.drawOval(50f,550f,350f,750f,paint)

        //画线 drawLine()
        canvas?.drawLine(50f,50f,350f,100f,paint1)
        val points = floatArrayOf(1000f,170f,1000f,370f,1000f,270f,1100f,270f,1100f,170f,1100f,370f)
        canvas?.drawLines(points,paint4)

        //扇形/弧形 drawArc()
        canvas?.drawArc(400f,550f,750f,750f,-110f,100f,false,paint)
        canvas?.drawArc(400f,550f,750f,750f,90f,90f,true,paint4)
    }
}