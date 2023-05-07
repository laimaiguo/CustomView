package com.example.customview.draw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView


/**
 *Create by GWJ 2023/5/4 13:22
 **/
class MyTextView: AppCompatTextView {
    constructor(context: Context):super(context)
    constructor(context: Context,attr:AttributeSet):super(context,attr)

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawColor(Color.parseColor("#03DAC5"))
        super.onDraw(canvas)
    }
}