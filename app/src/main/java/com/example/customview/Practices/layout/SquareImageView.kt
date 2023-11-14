package com.example.customview.Practices.layout

import android.content.Context
import android.util.AttributeSet

/**
 *Create by GWJ 2023/5/9 15:39
 * 需要把它写成正方形的 ImageView
 **/
class SquareImageView : androidx.appcompat.widget.AppCompatImageView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // 先用 getMeasuredWidth() 和 getMeasuredHeight() 取到 super.onMeasure() 的计算结果
        var measureWidth = measuredWidth
        var measureHeight = measuredHeight
        // 然后通过计算，让宽度和高度一致
        if (measureWidth > measureHeight) {
            measureWidth = measureHeight
        } else {
            measureHeight = measureWidth
        }
        // 再用 setMeasuredDimension(width, height) 来保存最终的宽度和高度
        setMeasuredDimension(measureWidth, measureHeight)
    }
}