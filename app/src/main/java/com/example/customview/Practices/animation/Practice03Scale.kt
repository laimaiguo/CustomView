package com.example.customview.Practices.animation

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.customview.R
import org.jetbrains.annotations.Nullable

class Practice03Scale : RelativeLayout {
    var animateBt: Button? = null
    var imageView: ImageView? = null
    var index = 0
    var maxIndex = 7

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, @Nullable attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, @Nullable attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animateBt = findViewById<View>(R.id.animateBt) as Button
        imageView = findViewById<View>(R.id.imageView) as ImageView
        animateBt!!.setOnClickListener {
            // TODO 在这里处理点击事件，通过 View.animate().scaleX/Y() 来让 View 放缩
            when(index){
                0 -> imageView!!.animate().scaleX(1.8f)
                1 -> imageView!!.animate().scaleX(1f)
                2 -> imageView!!.animate().scaleY(2f)
                3 -> imageView!!.animate().scaleY(1f)
                4 -> imageView!!.animate().scaleX(-2f)
                5 -> imageView!!.animate().scaleY(-2f)
                6 -> imageView!!.animate().scaleX(1f).scaleY(1f)

            }
            index ++
            if (index == maxIndex){
                index = 0
            }
        }
    }
}