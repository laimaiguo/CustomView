package com.example.customview.Practices

import android.content.res.Resources

object Utils {
    fun dpToPixel(dp: Float): Float {
        val metrics = Resources.getSystem().displayMetrics
        return dp * metrics.density
    }
}