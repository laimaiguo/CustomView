package com.example.customview.Practices.animation

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.customview.Practices.Utils
import com.example.customview.R
import org.jetbrains.annotations.Nullable

class Practice05MultiProperties : ConstraintLayout {
    var animateBt: Button? = null
    var imageView: ImageView? = null
    var show = false

    constructor(context: Context) : super(context) {}
    constructor(context: Context, @Nullable attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context, @Nullable attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    protected override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animateBt = findViewById(R.id.animateBt)
        imageView = findViewById(R.id.imageView)
        imageView!!.scaleX = 0f
        imageView!!.scaleY = 0f
        imageView!!.alpha = 0f
        animateBt!!.setOnClickListener {
            // TODO 在这里处理点击事件，同时对多个属性做动画
            when(show){
                false -> {
                    imageView!!.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .alpha(1f)
                        .rotation(360f)
                        .translationX(Utils.dpToPixel(400f))
                        .duration = 1200
                }
                true -> {
                    imageView!!.animate()
                        .scaleX(0f)
                        .scaleY(0f)
                        .alpha(0f)
                        .rotation(0f)
                        .translationX(0f)
                        .duration = 1200
                }
            }
            show = !show
        }
    }
}