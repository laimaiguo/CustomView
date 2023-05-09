package com.example.customview.Practices.animation

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import com.example.customview.Practices.Utils
import com.example.customview.R
import org.jetbrains.annotations.Nullable

class Practice06Duration : LinearLayout {
    var durationSb: SeekBar? = null
    var durationValueTv: TextView? = null
    var animateBt: Button? = null
    var imageView: ImageView? = null
    var duration = 300
    var show = false

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
        durationSb = findViewById<View>(R.id.durationSb) as SeekBar
        durationValueTv = findViewById<View>(R.id.durationValueTv) as TextView
        durationValueTv!!.text = context.getString(R.string.ms_with_value, duration)
        durationSb!!.max = 10
        durationSb!!.progress = 1
        durationSb!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                duration = progress * 300
                durationValueTv!!.text = context.getString(R.string.ms_with_value, duration)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        animateBt = findViewById<View>(R.id.animateBt) as Button
        imageView = findViewById<View>(R.id.imageView) as ImageView
        imageView!!.animate().scaleX(0.5f).scaleY(0.5f)
        animateBt!!.setOnClickListener {
            // TODO 在这里处理点击事件，执行动画。记得使用 `setDuration(duration)` 来设置动画的时长。
            when(show){
                false -> {
                    imageView!!.animate()
                        .scaleX(1.2f)
                        .scaleY(1.2f)
                        .rotation(360f)
                        .translationX(Utils.dpToPixel(400f))
                        .duration = duration.toLong()
                }
                true -> {
                    imageView!!.animate()
                        .scaleX(0.5f)
                        .scaleY(0.5f)
                        .rotation(0f)
                        .translationX(0f)
                        .duration = duration.toLong()
                }
            }
            show = !show
        }
    }
}