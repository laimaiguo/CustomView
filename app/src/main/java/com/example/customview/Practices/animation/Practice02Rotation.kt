package com.example.customview.Practices.animation

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.customview.R
import org.jetbrains.annotations.Nullable

class Practice02Rotation : RelativeLayout {
    var animateBt: Button? = null
    var imageView: ImageView? = null
    var index = 0
    var maxIndex = 8

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
            // // TODO 在这里处理点击事件，通过 View.animate().rotation/X/Y() 来让 View 旋转
            when(index){
                0 -> imageView!!.animate().rotation(180f).duration = 1000
                1 -> imageView!!.animate().rotation(-270f).duration = 1000
                2 -> imageView!!.animate().rotationX(120f)
                3 -> imageView!!.animate().rotationX(0f)
                4 -> imageView!!.animate().rotationY(160f)
                5 -> imageView!!.animate().rotationY(0f)
                6 -> imageView!!.animate().rotationXBy(160f)
                7 -> imageView!!.animate().rotationYBy(160f)
            }
            index ++
            if (index == maxIndex){
                index = 0
            }
        }
    }
}