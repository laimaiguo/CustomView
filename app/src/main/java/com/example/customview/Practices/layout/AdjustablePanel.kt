package com.example.customview.Practices.layout

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.SeekBar
import com.example.customview.Practices.Utils
import kotlinx.android.synthetic.main.practice_layout_already.view.*

/**
 *Create by GWJ 2023/5/9 14:44
 * 这个类是用来做 ImageView 外部的可调整框架的，不必关注
 **/
class AdjustablePanel : RelativeLayout {

    val bottomMargin = Utils.dpToPixel(48f)
    val minWidth = Utils.dpToPixel(80f)
    val minHeight = Utils.dpToPixel(100f)

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val listener = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val layoutParams = parentLayout.layoutParams
                layoutParams.width =
                    ((minWidth + (width - minWidth) * widthBar.progress / 100).toInt())
                layoutParams.height =
                    ((minHeight + (height - bottomMargin - minHeight) * heightBar.progress / 100).toInt())
                parentLayout.layoutParams = layoutParams
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        }
        widthBar.setOnSeekBarChangeListener(listener)
        heightBar.setOnSeekBarChangeListener(listener)
        whBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val layoutParams = parentLayout.layoutParams
                layoutParams.width =
                    ((minWidth + (width - minWidth) * whBar.progress / 100).toInt())
                layoutParams.height =
                    ((minHeight + (height - bottomMargin - minHeight) * whBar.progress / 100).toInt())
                parentLayout.layoutParams = layoutParams
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

    }


}