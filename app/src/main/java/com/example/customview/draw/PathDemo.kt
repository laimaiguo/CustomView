package com.example.customview.draw

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.*
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import androidx.annotation.RequiresApi
import com.example.customview.R
import java.util.*

/**
 *Author GWJ
 *2022/1/19 13:54
 **/
class PathDemo : View {
    companion object{
        const val mWidth = 2000
        const val mHeight = 2000
    }
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    private val paintAngle = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paintLine = Paint(Paint.ANTI_ALIAS_FLAG)

    //    private val paintBlue = Paint(Paint.ANTI_ALIAS_FLAG)
//    private val paintGreen = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private val path1 = Path()
    private val pathLine = Path()
    private val test =false

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        /** ❤ **/
        love(canvas)

        /** FillType 相交圆/同心圆 **/
        intersectCircle(canvas)
        conCentricCircle(canvas)

        /** drawText **/
        text(canvas)

        /**画线*/
        line(canvas)
        /** shade 着色器 设置颜色 clamp mirror repeat **/

        LinearClamp(canvas)
        LinearMirror(canvas)
        LinearRepeat(canvas)

        radialClamp(canvas)
        radialMirror(canvas)
        radialRepeat(canvas)

        shapeSweep(canvas)
        shapeBitmap(canvas)
        shapeCompose(canvas)

        /**颜色过滤器*/
        paintColorFilter(canvas)

        /**线条*/
        strokecCap(canvas)
        strokecJoin(canvas)

        /**给图形的轮廓设置效果*/
        pathEffect(canvas)

        /**模糊、浮雕效果*/
        maskFilter(canvas)

        /**staticLayout*/
        staticLayout(canvas)



    }

    private fun staticLayout(canvas: Canvas?) {
        //staticLayout文字换行
        val textPaint = TextPaint()
        val strText = "As Android apps grow in size, it's important to define an architecture that allows the app to scale,\nincreases the app's robustness,\nand makes the app\neasier to test."
        val staticLayout = StaticLayout(strText,textPaint, 400,Layout.Alignment.ALIGN_NORMAL,1f,0f,true)
        canvas?.save()
        canvas?.translate(50f,470f)
        staticLayout.draw(canvas)
    }

    private fun maskFilter(canvas: Canvas?) {
        val bitmap1 = BitmapFactory.decodeResource(resources,R.mipmap.icon1m)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.strokeWidth = 2f
        paint.maskFilter = BlurMaskFilter(50f,BlurMaskFilter.Blur.NORMAL)
        canvas?.drawBitmap(bitmap1,1300f,1400f,paint)
    }

    private fun pathEffect(canvas: Canvas?) {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.STROKE
        paint.textSize = 34f
        paint.strokeCap = Paint.Cap.ROUND
        val pathEffOri = Path()
        pathEffOri.moveTo(80f,1650f)
        pathEffOri.lineTo(130f,1850f)
        pathEffOri.lineTo(250f,1550f)
        pathEffOri.lineTo(400f,1850f)
        pathEffOri.lineTo(520f,1700f)
        pathEffOri.lineTo(680f,1850f)
        canvas?.drawPath(pathEffOri,paint)

        //拐角变圆角
        val pathEffCorner = Path()
        pathEffCorner.moveTo(80f+720f,1650f)
        pathEffCorner.lineTo(130f+720f,1850f)
        pathEffCorner.lineTo(250f+720f,1550f)
        pathEffCorner.lineTo(400f+720f,1850f)
        pathEffCorner.lineTo(520f+720f,1700f)
        pathEffCorner.lineTo(680f+720f,1850f)
        val effectCorner = CornerPathEffect(70f)
        paint.pathEffect = effectCorner
        canvas?.drawPath(pathEffCorner,paint)
        canvas?.drawTextOnPath("Hello!This is a CustomViewDemo",pathEffCorner,1f,0f,paint)

        //线条随机偏离
        val pathEffDiscrete = Path()
        pathEffDiscrete.moveTo(80f,1650f+100f)
        pathEffDiscrete.lineTo(130f,1850f+100f)
        pathEffDiscrete.lineTo(250f,1550f+100f)
        pathEffDiscrete.lineTo(400f,1850f+100f)
        pathEffDiscrete.lineTo(520f,1700f+100f)
        pathEffDiscrete.lineTo(680f,1850f+100f)
        val effectDiscrete = DiscretePathEffect(50f,10f)
        paint.pathEffect = effectDiscrete
        canvas?.drawPath(pathEffDiscrete,paint)

        //虚线绘制线条
        val pathEffDash = Path()
        pathEffDash.moveTo(80f,1650f+200f)
        pathEffDash.lineTo(130f,1850f+200f)
        pathEffDash.lineTo(250f,1550f+200f)
        pathEffDash.lineTo(400f,1850f+200f)
        pathEffDash.lineTo(520f,1700f+200f)
        pathEffDash.lineTo(680f,1850f+200f)
        val effectDash = DashPathEffect(floatArrayOf(20f,10f,5f,10f),0f)
        paint.pathEffect = effectDash
        canvas?.drawPath(pathEffDash,paint)

        //使用path来绘制虚线
        val pathEffPathDash = Path()
        pathEffPathDash.moveTo(80f,1650f+300f)
        pathEffPathDash.lineTo(130f,1850f+300f)
        pathEffPathDash.lineTo(250f,1550f+300f)
        pathEffPathDash.lineTo(400f,1850f+300f)
        pathEffPathDash.lineTo(520f,1700f+300f)
        pathEffPathDash.lineTo(680f,1850f+300f)
        val dashPath = Path()
        dashPath.lineTo(80f,1850f)
        dashPath.lineTo(95f,1950f)

        val effectPathDash = PathDashPathEffect(dashPath,40f,0f,PathDashPathEffect.Style.TRANSLATE)
        paint.pathEffect = effectPathDash
        canvas?.drawPath(pathEffPathDash,paint)

        //组合-先后绘制
        val dashEffect = DashPathEffect(floatArrayOf(20f,10f),0f)
        val discreteEffect = DiscretePathEffect(20f,5f)
        val pathCompose = Path()
        pathCompose.moveTo(80f+720f,1650f+100f)
        pathCompose.lineTo(130f+720f,1850f+100f)
        pathCompose.lineTo(250f+720f,1550f+100f)
        pathCompose.lineTo(400f+720f,1850f+100f)
        pathCompose.lineTo(520f+720f,1700f+100f)
        pathCompose.lineTo(680f+720f,1850f+100f)
        val pathEffCorner1 = ComposePathEffect(dashEffect,discreteEffect)
//        val pathEffCorner1 = ComposePathEffect(discreteEffect,dashEffect)
        paint.pathEffect = pathEffCorner1
        canvas?.drawPath(pathCompose,paint)


    }

    private fun strokecJoin(canvas: Canvas?) {
        val paintMiter = Paint(Paint.ANTI_ALIAS_FLAG)
        paintMiter.strokeWidth = 20f
        paintMiter.strokeJoin = Paint.Join.MITER
        paintMiter.strokeMiter = 10f
        canvas?.drawLine(1250f,1250f,1550f,1250f,paintMiter)
        canvas?.drawLine(1550f,1250f,1350f,1350f,paintMiter)
    }

    private fun strokecCap(canvas: Canvas?) {
        val paintButt = Paint(Paint.ANTI_ALIAS_FLAG)
        paintButt.strokeWidth = 20f
        canvas?.drawLine(1250f,1148f,1550f,1148f,paintButt)

        val paintRount = Paint(Paint.ANTI_ALIAS_FLAG)
        paintRount.strokeWidth = 20f
        paintRount.strokeCap = Paint.Cap.ROUND
        canvas?.drawLine(1250f,1178f,1550f,1178f,paintRount)

        val paintSquare = Paint(Paint.ANTI_ALIAS_FLAG)
        paintSquare.strokeWidth = 20f
        paintSquare.strokeCap = Paint.Cap.SQUARE
        canvas?.drawLine(1250f,1208f,1550f,1208f,paintSquare)
    }

    private fun paintColorFilter(canvas: Canvas?) {
        /**
         * ColorFilter 颜色过滤器
         * 子类：LightingColorFilter、
         * PorterDuffColorFilter、
         * ColorMatrixColorFilter
         */
        val lightingColorFilter = LightingColorFilter(0x00ffff,0x003000)
        val porterDuffColorFilter = PorterDuffColorFilter(0x00ffff,PorterDuff.Mode.DST_OVER)

        paintAngle.colorFilter = porterDuffColorFilter
        val lightbitmap = BitmapFactory.decodeResource(resources,R.mipmap.icon1m)
        val lightshader = BitmapShader(lightbitmap,Shader.TileMode.REPEAT,Shader.TileMode.REPEAT)
        paintAngle.shader= lightshader
        canvas?.drawRect(800f,1148f,1200f,1530f,paintAngle)
    }

    private fun shapeCompose(canvas: Canvas?) {
        /**
         * ComposeShader 混合着色器
         * shaderA, shaderB：两个相继使用的 Shader
         * mode: 两个 Shader 的叠加模式，即 shaderA 和 shaderB 应该怎样共同绘制。它的类型是 PorterDuff.Mode 。src_over dst_out挖空
         */
        val bitmap1 = BitmapFactory.decodeResource(resources,R.mipmap.icon1m)
        val shader1 = BitmapShader(bitmap1,Shader.TileMode.REPEAT,Shader.TileMode.REPEAT)
        val bitmap2 = BitmapFactory.decodeResource(resources,R.mipmap.icon2m)
        val shader2 = BitmapShader(bitmap2,Shader.TileMode.REPEAT,Shader.TileMode.REPEAT)
        val composeShader = ComposeShader(shader1,shader2,PorterDuff.Mode.MULTIPLY)
        paintAngle.shader = composeShader
        canvas?.drawCircle(600f,1350f,250f,paintAngle)
    }

    private fun shapeBitmap(canvas: Canvas?) {
        /** BitmapShader 用bitmap的像素来作为图形或文字的填充
         * bitmap：用来做模板的 Bitmap 对象
         * tileX：横向的 TileMode
         * tileY：纵向的 TileMode
         */
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.icon2)
        val bitmapShaper = BitmapShader(bitmap,Shader.TileMode.MIRROR,Shader.TileMode.MIRROR)
        paint.shader = bitmapShaper
        canvas?.drawCircle(270f, 800f, 250f,paint)

        //给❤添加图片
        val bitmappath = BitmapFactory.decodeResource(resources,R.mipmap.icon1)
        val shaderPath = BitmapShader(bitmappath,Shader.TileMode.MIRROR,Shader.TileMode.REPEAT)
        paint.shader = shaderPath
        canvas?.drawPath(path,paint)

        val bitmapCir = BitmapFactory.decodeResource(resources,R.mipmap.icon1)
        val shaderCir = BitmapShader(bitmapCir,Shader.TileMode.MIRROR,Shader.TileMode.MIRROR)
        paint.shader = shaderCir
        canvas?.drawCircle(665f, 755f, 65f,paint)
    }

    private fun shapeSweep(canvas: Canvas?) {
        /** SweepGradient 扫描渐变
         * cx cy ：扫描的中心
         * color0：扫描的起始颜色
         * color1：扫描的终止颜色
         * **/
        val sweepShaper = SweepGradient(200f,1200f,Color.parseColor("#E91E63"),Color.parseColor("#2196F3"))
        paintAngle.shader = sweepShaper
        canvas?.drawCircle(170f,1200f,150f,paintAngle)
    }

    private fun radialRepeat(canvas: Canvas?) {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val shaderRadialREPEAT = RadialGradient(
            1400f,
            910f,
            100f,
            Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3"),
            Shader.TileMode.REPEAT
        )
        paint.shader = shaderRadialREPEAT
        canvas?.drawCircle(1400f, 910f, 200f, paint)
    }

    private fun radialMirror(canvas: Canvas?) {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val shaderRadialMIRROR = RadialGradient(
            850f,
            850f,
            100f,
            Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3"),
            Shader.TileMode.MIRROR
        )
        paint.shader = shaderRadialMIRROR
        canvas?.drawCircle(850f, 850f, 220f, paint)
    }

    private fun radialClamp(canvas: Canvas?) {
        /** RadialGradient 辐射渐变
         * centerX centerY：辐射中心的坐标
         * radius：辐射半径
         * centerColor：辐射中心的颜色
         * edgeColor：辐射边缘的颜色
         * tileMode：辐射范围之外的着色模式。
         * **/
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val shaderRadialClamp = RadialGradient(
            270f,
            800f,
            260f,
            Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3"),
            Shader.TileMode.CLAMP
        )
        paint.shader = shaderRadialClamp
        canvas?.drawCircle(270f, 800f, 250f, paint)
    }

    private fun LinearRepeat(canvas: Canvas?) {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val shaderREPEAT = LinearGradient(
            1100f,
            500f,
            1250f,
            600f,
            Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3"),
            Shader.TileMode.REPEAT
        )
        paint.shader = shaderREPEAT
        canvas?.drawRoundRect(1100f, 500f, 1600f, 700f, 30f, 30f, paint)
        paint.textSize = 48f
        val text = "REPEAT"
        canvas?.drawText(text, 1600f, 600f, paint)
    }

    private fun LinearMirror(canvas: Canvas?) {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val shaderMIRROR = LinearGradient(
            1100f,
            250f,
            1250f,
            350f,
            Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3"),
            Shader.TileMode.MIRROR
        )
        paint.shader = shaderMIRROR
        canvas?.drawRoundRect(1100f, 250f, 1600f, 450f, 30f, 30f, paint)
        paint.textSize = 48f
        val text = "MIRROR"
        canvas?.drawText(text, 1600f, 350f, paint)
    }

    private fun LinearClamp(canvas: Canvas?) {
        /** LinearGradient 线性渐变
         * x0 y0 x1 y1：渐变的两个端点的位置
         * color0 color1 是端点的颜色
         * tile：端点范围之外的着色规则
         * **/
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.textSize = 48f
        val shaderCLAMP = LinearGradient(
            1100f,
            0f,
            1600f,
            200f,
            Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3"),
            Shader.TileMode.CLAMP
        )
        paint.shader = shaderCLAMP
        canvas?.drawRoundRect(1100f, 0f, 1600f, 200f, 30f, 30f, paint)
        val text = "CLAMP"
        canvas?.drawText(text, 1600f, 100f, paint)
    }

    private fun line(canvas: Canvas?) {
        paintLine.color = Color.GREEN
        paintLine.style = Paint.Style.STROKE
        pathLine.moveTo(100f,100f)
        pathLine.lineTo(200f,100f)
        pathLine.lineTo(150f,150f)
        pathLine.close()
//        pathLine.rLineTo(400f,0f)
//        pathLine.arcTo(100f, 100f, 300f, 300f, -90f, 90f, true);

        canvas?.drawPath(pathLine,paintLine)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun text(canvas: Canvas?) {
        paintAngle.color = Color.GREEN
        paintAngle.textSize = 48f
        //添加阴影
        paintAngle.setShadowLayer(7f,2f,2f,Color.BLACK)
        //字体
        val text = "Hello,直沿雨骨PathDemo \uD83C\uDDE8\uD83C\uDDF3"
        paintAngle.textLocale = Locale.TAIWAN
        canvas?.drawText(text, 50f, 400f, paintAngle)
        paintAngle.textLocale = Locale.JAPAN
        //调整文字方向&上下文
        canvas?.drawTextRun(text,0,text.length,0,text.length,50f,450f,true,paintAngle)
//        paintAngle.isFakeBoldText = true

    }

    private fun conCentricCircle(canvas: Canvas?) {
        path1.addCircle(800f, 500f, 120f, Path.Direction.CW)
        path1.addCircle(800f, 500f, 70f, Path.Direction.CCW)
        path1.fillType = Path.FillType.WINDING
        paintAngle.color = Color.BLUE
//        paintAngle.style = Paint.Style.STROKE
        canvas?.drawPath(path1, paintAngle)
    }

    private fun intersectCircle(canvas: Canvas?) {
        path1.addCircle(800f, 200f, 100f, Path.Direction.CW)
        path1.addCircle(900f, 200f, 100f, Path.Direction.CCW)
        path1.fillType = Path.FillType.EVEN_ODD
        canvas?.drawPath(path1, paintAngle)
    }

    private fun love(canvas: Canvas?) {
        paintAngle.color = Color.RED
        path.addArc(0f, 0f, 200f, 200f, -225f, 225f)
        path.arcTo(200f, 0f, 400f, 200f, -180f, 225f, false)
        path.lineTo(200f, 342f)
//even_odd   winding   inverse_even_odd   inverse_winding
        canvas?.drawPath(path, paintAngle)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //在scrollview里套自定义的子view，除了要给scrollview设置fillViewport属性，还要重写onMeasure方法，宽高大于屏幕的
        setMeasuredDimension(mWidth, mHeight)
    }

}