package com.example.customview.Practices.animation

import android.content.Context
import android.graphics.Outline
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import com.example.customview.R
import com.example.customview.Practices.Utils.dpToPixel
import org.jetbrains.annotations.Nullable

class Practice01Translation : RelativeLayout {
    var animateBt: Button? = null
    var imageView: ImageView? = null
    var index = 0
    val maxIndex = 6

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
        animateBt = findViewById<View>(R.id.animateBt) as Button?
        imageView = findViewById<View>(R.id.imageView) as ImageView?
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            // 给音乐图标加上合适的阴影
            imageView!!.outlineProvider = MusicOutlineProvider()
        }
        animateBt!!.setOnClickListener {
            // TODO 在这里处理点击事件，通过 View.animate().translationX/Y/Z() 来让 View 平移
            when(index){
                0 -> imageView!!.animate().translationX(dpToPixel(400f))
                1 -> imageView!!.animate().translationX(0f)
                2 -> imageView!!.animate().translationY(dpToPixel(100f))
                3 -> imageView!!.animate().translationY(0f)
                4 -> imageView!!.animate().translationX(dpToPixel(150f)).translationY(dpToPixel(150f))
                5 -> imageView!!.animate().translationX(0f).translationY(0f)

            }
            index ++
            if (index == maxIndex){
                index = 0
            }
        }
    }

    /**
     * 为音乐图标设置三角形的 Outline。
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    internal inner class MusicOutlineProvider : ViewOutlineProvider() {
        var path = Path()
        override fun getOutline(view: View, outline: Outline) {
            outline.setConvexPath(path)
        }

        init {
            path.moveTo(0f, dpToPixel(10f))
            path.lineTo(dpToPixel(7f), dpToPixel(2f))
            path.lineTo(dpToPixel(116f), dpToPixel(58f))
            path.lineTo(dpToPixel(116f), dpToPixel(70f))
            path.lineTo(dpToPixel(7f), dpToPixel(128f))
            path.lineTo(0f, dpToPixel(120f))
            path.close()
        }
    }

   /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
    {
        imageView!!.animate().translationZ(Utils.dpToPixel(15f))
    }*/
}