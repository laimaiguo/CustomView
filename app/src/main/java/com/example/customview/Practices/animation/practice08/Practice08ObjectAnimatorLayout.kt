package com.example.customview.Practices.animation.practice08

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.Button
import android.widget.RelativeLayout
import com.example.customview.R
import kotlinx.android.synthetic.main.practice_object_animator.view.*

class Practice08ObjectAnimatorLayout : RelativeLayout {
    var view: Practice08ObjectAnimatorView? = null
    var animateBt: Button? = null
    var maxValues = 65f
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        view = findViewById<View>(R.id.objectAnimatorView) as Practice08ObjectAnimatorView
        animateBt = findViewById<View>(R.id.animateBt) as Button

        animateBt!!.setOnClickListener {
            // TODO 在这里处理点击事件，用 ObjectAnimator 播放动画
            /** 1. 用 ObjectAnimator 创建 Animator 对象
              * 2. 用 start() 执行动画
              * 3. 记得在 Practice08ObjectAnimatorView 中为 progress 添加 setter/ getter 方法！
             */
            if (maxValue.text!!.isNotEmpty()) maxValues = maxValue.text.toString().toFloat()
            val animator = ObjectAnimator.ofFloat(view,"progress1",0f,maxValues)
            animator.duration = 1000
            animator.interpolator = AccelerateInterpolator()
            animator.start()
        }
    }
}