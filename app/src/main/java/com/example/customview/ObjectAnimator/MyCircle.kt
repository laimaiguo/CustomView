package com.example.customview.ObjectAnimator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 *Create by GWJ 2023/5/8 10:11
 **/
class MyCircle: View {
    constructor(context: Context):super(context)
    constructor(context: Context,attr:AttributeSet):super(context,attr)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        canvas?.drawCircle(250f,250f,200f,paint)
    }
}