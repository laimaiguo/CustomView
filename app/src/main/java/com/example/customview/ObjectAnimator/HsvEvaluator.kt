package com.example.customview.ObjectAnimator

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.graphics.Color

/**
 *Create by GWJ 2023/5/8 10:16
 **/
class HsvEvaluator:TypeEvaluator<Int> {
    val startHsv = FloatArray(3)
    var endHsv = FloatArray(3)
    var outHsv = FloatArray(3)
    override fun evaluate(fraction: Float, startValue: Int?, endValue: Int?): Int {
        //把 ARGB 转换成 HSV
        Color.colorToHSV(startValue!!,startHsv)
        Color.colorToHSV(endValue!!,endHsv)

        //计算当前动画完成度(fraction)所对应的颜色值
        if(endHsv[0] - startHsv[0] > 180){
            endHsv[0] = 360 - endHsv[0]
        }else if(endHsv[0] - startHsv[0] < -180){
            endHsv[0] = 360 + endHsv[0]
        }
        outHsv[0] = startHsv[0] + (endHsv[0] - startHsv[0]) * fraction
        if (outHsv[0] > 360) {
            outHsv[0] = 360 - outHsv[0]
        } else if (outHsv[0] < 0) {
            outHsv[0] = 360 + outHsv[0]
        }
        outHsv[1] = startHsv[1] + (endHsv[1] - startHsv[1]) * fraction
        outHsv[2] = startHsv[2] + (endHsv[2] - startHsv[2]) * fraction

        // 计算当前动画完成度（fraction）所对应的透明度 shr右移<<
        val alpha = startValue shr 24 + ((endValue shr 24 - startValue shr 24) * fraction).toInt()

        // 把 HSV 转换回 ARGB 返回
        return Color.HSVToColor(alpha, outHsv)
    }
}
