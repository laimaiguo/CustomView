package com.example.customview.draw

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.shapes.Shape
import android.provider.MediaStore
import android.util.AttributeSet
import android.view.View
import com.example.customview.R

/**
 *Author GWJ
 *2022/1/19 13:54
 **/
class PathDemo : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    //    private val paintBlue = Paint(Paint.ANTI_ALIAS_FLAG)
//    private val paintGreen = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private val path1 = Path()
    private val test =false

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        /** ❤ **/
        paint.color = Color.RED
        path.addArc(0f, 0f, 200f, 200f, -225f, 225f)
        path.arcTo(200f, 0f, 400f, 200f, -180f, 225f, false)
        path.lineTo(200f, 342f)
//even_odd   winding   inverse_even_odd   inverse_winding
        canvas?.drawPath(path, paint)

        /** FillType **/
        path1.addCircle(800f, 200f, 100f, Path.Direction.CW)
        path1.addCircle(900f, 200f, 100f, Path.Direction.CCW)
        path1.fillType = Path.FillType.EVEN_ODD

        path1.addCircle(800f, 500f, 120f, Path.Direction.CW)
        path1.addCircle(800f, 500f, 70f, Path.Direction.CCW)
        path1.fillType = Path.FillType.WINDING
        paint.color = Color.BLUE
        canvas?.drawPath(path1, paint)

        /** drawText **/
        paint.color = Color.GREEN
        paint.textSize = 48f
        var text = "Hello,PathDemo"
        canvas?.drawText(text, 50f, 400f, paint)

        /** shade 着色器 设置颜色 clamp mirror repeat **/

        /** LinearGradient 线性渐变
         * x0 y0 x1 y1：渐变的两个端点的位置
         * color0 color1 是端点的颜色
         * tile：端点范围之外的着色规则
         * **/
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
        paint.textSize = 48f
        text = "CLAMP"
        canvas?.drawText(text, 1600f, 100f, paint)

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
        text = "MIRROR"
        canvas?.drawText(text, 1600f, 350f, paint)

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
        text = "REPEAT"
        canvas?.drawText(text, 1600f, 600f, paint)


        /** RadialGradient 辐射渐变
         * centerX centerY：辐射中心的坐标
         * radius：辐射半径
         * centerColor：辐射中心的颜色
         * edgeColor：辐射边缘的颜色
         * tileMode：辐射范围之外的着色模式。
         * **/
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


        /** SweepGradient 扫描渐变
         * cx cy ：扫描的中心
         * color0：扫描的起始颜色
         * color1：扫描的终止颜色
         * **/
        val sweepShaper = SweepGradient(200f,1200f,Color.parseColor("#E91E63"),Color.parseColor("#2196F3"))
        paint.shader = sweepShaper
        canvas?.drawCircle(170f,1200f,150f,paint)


        /** BitmapShader 用bitmap的像素来作为图形或文字的填充
         * bitmap：用来做模板的 Bitmap 对象
         * tileX：横向的 TileMode
         * tileY：纵向的 TileMode
         */
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.icon2)
        val bitmapShaper = BitmapShader(bitmap,Shader.TileMode.MIRROR,Shader.TileMode.MIRROR)
        paint.shader = bitmapShaper
        canvas?.drawCircle(270f, 800f, 250f,paint)

        //给❤添加图片
        val bitmappath = BitmapFactory.decodeResource(resources,R.mipmap.icon1)
        val shaderPath = BitmapShader(bitmappath,Shader.TileMode.MIRROR,Shader.TileMode.REPEAT)
        paint.shader = shaderPath
        canvas?.drawPath(path,paint)

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
        paint.shader = composeShader
        canvas?.drawCircle(600f,1350f,250f,paint)


        /**
         * ColorFilter 颜色过滤器
         * 子类：LightingColorFilter、
         * PorterDuffColorFilter、
         * ColorMatrixColorFilter
         */
        val lightingColorFilter = LightingColorFilter(0x00ffff,0x003000)
        val porterDuffColorFilter = PorterDuffColorFilter(0x00ffff,PorterDuff.Mode.DST_OVER)

        paint.colorFilter = porterDuffColorFilter
        val lightbitmap = BitmapFactory.decodeResource(resources,R.mipmap.icon1m)
        val lightshader = BitmapShader(lightbitmap,Shader.TileMode.REPEAT,Shader.TileMode.REPEAT)
        paint.shader= lightshader
//        paint.maskFilter =
        canvas?.drawRect(800f,1148f,1200f,1530f,paint)


    }
}