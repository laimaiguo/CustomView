package com.example.customview.draw

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.customview.MainActivity
import com.example.customview.R
import java.nio.file.attribute.AttributeView

/**
 *Author GWJ
 *2022/1/14 15:42
 **/
class CanvasDemo : View {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG) //打开抗锯齿,消除毛边现象
    private val paint1 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paint2 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paint3 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paint4 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paint5 = Paint(Paint.ANTI_ALIAS_FLAG)

    var progress = 0f

    constructor(context: Context):super(context)
    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet)

    @JvmName("getProgress1")
    public fun getProgress():Float{
        return progress
    }
    @JvmName("setProgress1")
    public fun setProgress(progress:Float){
        this.progress = progress
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        /**圆*/
        circle(canvas)
        /**矩形*/
        rect(canvas)
        /**点*/
        point(canvas)
        /**线*/
        line(canvas)
        /**扇形弧形*/
        arc(canvas)
        /**范围裁切*/
        clipRect(canvas)
        clipPath(canvas)

        /**几何变换*/
        rotate(canvas)
        scale(canvas)
        skew(canvas)
        matrixDemo(canvas)
        cameraDemo(canvas)


        //绘制之前设置底色；绘制之后为界面设置半透明蒙版
        canvas?.drawColor(Color.parseColor("#4D99D2FF"))

    }

//    val annimator = ObjectAnimator.ofFloat(progress,"progress",0, 65)
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(MainActivity.mWidth,MainActivity.mHeight)
    }
    fun circle(canvas: Canvas?){
        paint.color = Color.BLUE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5F

        paint1.color = Color.RED
        paint1.style = Paint.Style.STROKE
        paint1.strokeWidth = 5F

        paint2.color = Color.GREEN
        //圆形 drawCircle()
        canvas?.drawCircle(300f,300f,100f,paint)
        canvas?.drawCircle(400f,400f,100f,paint1)
        canvas?.drawCircle(500f,300f,100f,paint2)
        canvas?.drawCircle(600f,400f,100f,paint1)
        canvas?.drawCircle(700f,300f,100f,paint)
        //椭圆 drawOval()
        canvas?.drawOval(50f,550f,350f,750f,paint)
    }
    fun rect(canvas: Canvas?){
        //矩形 drawRect()
        canvas?.drawRect(400f,50f,500f,150f,paint2)
        canvas?.drawRect(200f,200f,800f,500f,paint1)
        //圆角矩形 drawRoundRect()
        canvas?.drawRoundRect(530f,50f,830f,150f,30f,30f,paint2)
        canvas?.drawRoundRect(850f,50f,1150f,150f,30f,30f,paint1)
    }
    fun point(canvas: Canvas?){
        paint3.color = Color.BLACK
        paint3.strokeWidth = 10f
        paint3.strokeCap = Paint.Cap.ROUND

        paint5.color = Color.DKGRAY
        paint5.strokeCap = Paint.Cap.BUTT
        paint5.strokeWidth = 5f

        //点 drawPoint()
        canvas?.drawPoint(300f,300f,paint3)
        canvas?.drawPoint(600f,400f,paint3)
        canvas?.drawPoint(500f,300f,paint5)
    }
    fun line(canvas: Canvas?){
        paint4.color = Color.CYAN
        paint4.style = Paint.Style.FILL
        //画线 drawLine()
        canvas?.drawLine(50f,50f,350f,100f,paint1)
        val points = floatArrayOf(1000f,170f,1000f,370f,1000f,270f,1100f,270f,1100f,170f,1100f,370f)
        canvas?.drawLines(points,paint4)
    }
    fun arc(canvas: Canvas?){
        //扇形/弧形 drawArc()
        canvas?.drawArc(400f,550f,750f,750f,-110f,100f,false,paint)
        canvas?.drawArc(400f,550f,750f,750f,90f,90f,true,paint4)
    }
    fun clipRect(canvas: Canvas?){
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.icon1m)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        canvas?.save()
        canvas?.clipRect(20f,770f,300f,950f)
        canvas?.drawBitmap(bitmap,10f,700f,paint)
        canvas?.restore()
    }
    fun clipPath(canvas: Canvas?){
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.icon1m)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val path = Path()
        path.addCircle(500f,870f,100f,Path.Direction.CW)
        canvas?.save()
        canvas?.clipPath(path)
        canvas?.drawBitmap(bitmap,310f,600f,paint)
        canvas?.restore()
    }
    fun rotate(canvas: Canvas?){
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.icon1m)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        canvas?.save()
        canvas?.rotate(45f)
        canvas?.drawBitmap(bitmap,600f,700f,paint)
        canvas?.restore()
    }
    fun scale(canvas: Canvas?){
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.icon1m)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        canvas?.save()
        canvas?.scale(0.3f,0.3f,470f+bitmap.width/2,500f+bitmap.height/2)
        canvas?.drawBitmap(bitmap,470f,500f,paint)
        canvas?.restore()

        canvas?.save()
        canvas?.scale(0.5f,1.1f,650f+bitmap.width/2,500f+bitmap.height/2)
        canvas?.drawBitmap(bitmap,650f,500f,paint)
        canvas?.restore()
    }
    fun skew(canvas: Canvas?){
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.icon1m)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        canvas?.save()
        canvas?.scale(0.3f,0.3f,600f+bitmap.width/2,700f+bitmap.height/2)
        canvas?.skew(-0.5f,0f)
        canvas?.drawBitmap(bitmap,600f,700f,paint)
        canvas?.restore()
    }
    fun matrixDemo(canvas: Canvas?){
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.icon1)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val mMatrix = Matrix()
        val pointsSrc = floatArrayOf(left.toFloat(),
            top.toFloat(), right.toFloat(), top.toFloat(), left.toFloat(),
            bottom.toFloat(), right.toFloat(), bottom.toFloat()
        )
        val pointsDst = floatArrayOf((left - 20).toFloat(),
            (top + 150).toFloat(),
            (right + 120).toFloat(),
            (top - 90).toFloat(), (left + 20).toFloat(), (bottom + 30).toFloat(),
            (right + 20).toFloat(), (bottom + 60).toFloat()
        )
        mMatrix.reset()
        mMatrix.setPolyToPoly(pointsSrc,0,pointsDst,0,4)
        canvas?.save()
        canvas?.concat(mMatrix)
        canvas?.drawBitmap(bitmap,200f,900f,paint)
        canvas?.restore()
    }
    fun cameraDemo(canvas: Canvas?){
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.icon1)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        canvas?.save()
        canvas?.scale(0.7f,0.7f)
        val mCamera = Camera()
        mCamera.rotateX(30f)
        mCamera.applyToCanvas(canvas)
//        mCamera.restore()
        canvas?.drawBitmap(bitmap,1150f,80f,paint)
        canvas?.restore()
    }
}