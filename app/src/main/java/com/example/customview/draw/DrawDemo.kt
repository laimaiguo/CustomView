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
class DrawDemo : View {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG) //打开抗锯齿,消除毛边现象
    private val paint1 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paint2 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paint3 = Paint(Paint.ANTI_ALIAS_FLAG)



    constructor(context: Context):super(context)
    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet)


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.BLUE
        paint1.color = Color.RED
        paint2.color = Color.GREEN
        paint3.color = Color.BLACK

        paint.style = Paint.Style.STROKE
        paint1.style = Paint.Style.STROKE

        paint.strokeWidth = 5F
        paint1.strokeWidth = 5F
        paint3.strokeWidth = 10f

        paint3.strokeCap = Paint.Cap.ROUND

        //圆形
        canvas?.drawCircle(300f,300f,100f,paint)
        canvas?.drawCircle(400f,400f,100f,paint1)
        canvas?.drawCircle(500f,300f,100f,paint2)
        canvas?.drawCircle(600f,400f,100f,paint1)
        canvas?.drawCircle(700f,300f,100f,paint)

        //矩形
        canvas?.drawRect(200f,200f,300f,300f,paint2)
        canvas?.drawRect(200f,200f,800f,500f,paint1)

        //点
        canvas?.drawPoint(300f,300f,paint3)
        canvas?.drawPoint(600f,400f,paint3)

    }
}