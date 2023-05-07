package com.example.customview.draw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 *Create by GWJ 2023/5/4 13:51
 **/
class MyLinearLayout:LinearLayout {
    constructor(context: Context):super(context)
    constructor(context: Context,attr:AttributeSet):super(context,attr)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.parseColor("#9AFF86AF")
        canvas?.drawCircle(40f,40f,10f,paint)
        canvas?.drawCircle(200f,80f,20f,paint)
        canvas?.drawCircle(110f,100f,30f,paint)
        canvas?.drawCircle(200f,175f,40f,paint)
    }
}