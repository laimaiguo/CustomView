package com.example.customview.draw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import java.util.*


/**
 *Create by GWJ 2023/5/4 13:22
 **/
class MyTextView: AppCompatTextView {
    constructor(context: Context):super(context)
    constructor(context: Context,attr:AttributeSet):super(context,attr)
    constructor(context: Context,attr: AttributeSet,defStyle:Int):super(context,attr,defStyle)

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawColor(Color.parseColor("#9AFF86AF"))
        super.onDraw(canvas)
        this.setOnClickListener {
            text = randomText()
            invalidate()
        }
    }

    private fun randomText(): String {
        val random = Random()
        val set = HashSet<Int>()
        while (set.size < 4) {
            val randomInt = random.nextInt(10)
            set.add(randomInt)
        }
        val sbuffer = StringBuffer()
        for (i in set){
            sbuffer.append(""+i)
        }
        return "点击可生成新的4位数字：${sbuffer}"
    }
}