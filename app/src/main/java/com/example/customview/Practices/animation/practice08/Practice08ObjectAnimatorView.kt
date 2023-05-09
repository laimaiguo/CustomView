package com.example.customview.Practices.animation.practice08

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.customview.Practices.Utils.dpToPixel
import org.jetbrains.annotations.Nullable

class Practice08ObjectAnimatorView : View {
    val radius: Float = dpToPixel(80f)
    var arcRectF: RectF = RectF()
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // TODO 为 progress 添加 getter 和 setter 方法（setter 方法记得加 invalidate()）
    var progress = 0f

    @JvmName("getProgress1")
    fun getProgress(): Float { return progress}
    @JvmName("setProgress1")
    fun setProgress(progress:Float){
        this.progress = progress
        invalidate()
    }
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, @Nullable attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, @Nullable attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }


    public override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerX = (width / 2).toFloat()
        val centerY = (height / 2).toFloat()
        paint.color = Color.parseColor("#E91E63")
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = dpToPixel(15f)
        arcRectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
        canvas.drawArc(arcRectF, 135f, progress * 3.6f, false, paint)
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
        canvas.drawText(
            "$progress%",
            centerX,
            centerY - (paint.ascent() + paint.descent()) / 2,
            paint
        )
    }

    init {
        paint.textSize = dpToPixel(40f)
        paint.textAlign = Paint.Align.CENTER
    }
}