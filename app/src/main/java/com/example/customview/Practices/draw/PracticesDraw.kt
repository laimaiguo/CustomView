package com.example.customview.Practices.draw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.customview.MainActivity
import java.util.*
import kotlin.collections.HashSet

/**
 *Create by GWJ 2023/4/21 17:43
 **/
class PracticesDraw : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(MainActivity.mWidth, MainActivity.mHeight)
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //使用 canvas.drawColor() 方法把 View 涂成黄色
        colorYellow(canvas)
        /**使用 canvas.drawCircle() 方法画圆
        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆*/
        circle(canvas)
        //使用 canvas.drawRect() 方法画矩形
        rect(canvas)
        //使用 canvas.drawPoint() 方法画圆点和方点
        point(canvas)
        //使用 canvas.drawOval() 方法画椭圆
        oval(canvas)
        //使用 canvas.drawLine() 方法画直线
        line(canvas)
        //使用 canvas.drawRoundRect() 方法画圆角矩形
        rountRect(canvas)
        //使用 canvas.drawArc() 方法画弧形和扇形
        arc(canvas)
        //使用 canvas.drawPath() 方法画心形
        heart(canvas)
        //使用各种 Canvas.drawXXX() 方法画直方图
        histogram(canvas)
        //使用各种 Canvas.drawXXX() 方法画饼图
        pie(canvas)
    }


    private fun colorYellow(canvas: Canvas?) {
        val paintColor = Paint(Paint.ANTI_ALIAS_FLAG)
        paintColor.color = Color.YELLOW
        canvas?.drawRect(0f, 100f, 200f, 300f, paintColor)
    }

    private fun circle(canvas: Canvas?) {
        val solidCircle = Paint(Paint.ANTI_ALIAS_FLAG)
        solidCircle.color = Color.rgb(100, 181, 246)
        canvas?.drawCircle(350f, 200f, 100f, solidCircle)

        val hollowCircle = Paint(Paint.ANTI_ALIAS_FLAG)
        hollowCircle.style = Paint.Style.STROKE
        canvas?.drawCircle(600f, 200f, 100f, hollowCircle)

        val bluSolidCircle = Paint(Paint.ANTI_ALIAS_FLAG)
        bluSolidCircle.color = Color.BLUE
        canvas?.drawCircle(900f, 200f, 100f, bluSolidCircle)

        val lineHollowCircle = Paint(Paint.ANTI_ALIAS_FLAG)
        lineHollowCircle.strokeWidth = 20f
        lineHollowCircle.color = Color.rgb(225, 138, 101)
        lineHollowCircle.style = Paint.Style.STROKE
        canvas?.drawCircle(120f, 450f, 100f, lineHollowCircle)
    }

    private fun rect(canvas: Canvas?) {
        val painRect = Paint(Paint.ANTI_ALIAS_FLAG)
        painRect.color = Color.rgb(174, 213, 129)
        canvas?.drawRect(300f, 350f, 600f, 550f, painRect)
    }

    private fun point(canvas: Canvas?) {
        val paintCirclePoint = Paint(Paint.ANTI_ALIAS_FLAG)
        paintCirclePoint.strokeCap = Paint.Cap.ROUND
        paintCirclePoint.strokeWidth = 50f
        paintCirclePoint.color = Color.rgb(186, 104, 200)
        canvas?.drawPoint(670f, 350f, paintCirclePoint)

        val paintSquarePoint = Paint(Paint.ANTI_ALIAS_FLAG)
        paintSquarePoint.strokeCap = Paint.Cap.SQUARE
        paintSquarePoint.strokeWidth = 50f
        paintSquarePoint.color = Color.BLUE
        canvas?.drawPoint(750f, 350f, paintSquarePoint)
    }

    private fun oval(canvas: Canvas?) {
        val paintOval = Paint(Paint.ANTI_ALIAS_FLAG)
        paintOval.color = Color.rgb(229, 115, 115)
        canvas?.drawOval(670f, 400f, 1000f, 550f, paintOval)
    }

    private fun line(canvas: Canvas?) {
        val paintLine = Paint(Paint.ANTI_ALIAS_FLAG)
        paintLine.strokeWidth = 5f
        paintLine.color = Color.RED
        canvas?.drawLine(0f, 600f, 200f, 600f, paintLine)
    }

    private fun rountRect(canvas: Canvas?) {
        val paintRountRect = Paint(Paint.ANTI_ALIAS_FLAG)
        paintRountRect.color = Color.rgb(174, 213, 219)
        canvas?.drawRoundRect(0f, 620f, 200f, 820f, 30f, 30f, paintRountRect)
    }

    private fun arc(canvas: Canvas?) {
        val paintSector = Paint(Paint.ANTI_ALIAS_FLAG)
        paintSector.color = Color.rgb(77, 182, 172)
        //连接圆心是扇形
        canvas?.drawArc(200f, 600f, 400f, 800f, -90f, 120f, true, paintSector)

        val paintAcr = Paint(Paint.ANTI_ALIAS_FLAG)
        paintAcr.color = Color.rgb(77, 182, 172)
        //不连接圆心是弧形
        canvas?.drawArc(300f, 600f, 500f, 800f, -90f, 120f, false, paintAcr)
    }

    private fun heart(canvas: Canvas?) {
        val paintPath = Paint(Paint.ANTI_ALIAS_FLAG)
        paintPath.color = Color.rgb(225, 151, 226)
        val path = Path()
        path.addArc(600f, 600f, 800f, 800f, -225f, 225f)
        path.arcTo(800f, 600f, 1000f, 800f, -180f, 225f, false)
        path.lineTo(800f, 950f)
        canvas?.drawPath(path, paintPath)
    }

    private fun histogram(canvas: Canvas?) {
        val paintHistogram = Paint(Paint.ANTI_ALIAS_FLAG)
        paintHistogram.strokeWidth = 2f
        paintHistogram.textSize = 30f

        val paintRect = Paint(Paint.ANTI_ALIAS_FLAG)
        paintRect.style = Paint.Style.FILL_AND_STROKE
        paintRect.strokeWidth = 1f
        paintRect.color = Color.rgb(144,140,255)

        //y轴
        canvas?.drawLine(50f, 1000f, 50f, 1750f, paintHistogram)
        canvas?.drawLine(50f, 1000f, 20f, 1050f, paintHistogram)
        canvas?.drawLine(50f, 1000f, 80f, 1050f, paintHistogram)
        canvas?.drawText("y", 90f, 1000f, paintHistogram)

        //y轴单元格，每格150f
        canvas?.drawLine(50f, 1150f, 65f, 1150f, paintHistogram)
        canvas?.drawLine(50f, 1300f, 65f, 1300f, paintHistogram)
        canvas?.drawLine(50f, 1450f, 65f, 1450f, paintHistogram)
        canvas?.drawLine(50f, 1600f, 65f, 1600f, paintHistogram)
        canvas?.drawText("0", 50f, 1780f, paintHistogram)
        canvas?.drawText("15", 0f, 1620f, paintHistogram)
        canvas?.drawText("30", 0f, 1470f, paintHistogram)
        canvas?.drawText("45", 0f, 1320f, paintHistogram)
        canvas?.drawText("60", 0f, 1170f, paintHistogram)



        //x轴
        canvas?.drawLine(50f, 1750f, 800f, 1750f, paintHistogram)
        canvas?.drawLine(800f, 1750f, 750f, 1720f, paintHistogram)
        canvas?.drawLine(800f, 1750f, 750f, 1780f, paintHistogram)
        canvas?.drawText("x", 830f, 1750f, paintHistogram)

        //x轴单元格，每格150f
        canvas?.drawLine(200f, 1750f, 200f, 1735f, paintHistogram)
        canvas?.drawLine(350f, 1750f, 350f, 1735f, paintHistogram)
        canvas?.drawLine(500f, 1750f, 500f, 1735f, paintHistogram)
        canvas?.drawLine(650f, 1750f, 650f, 1735f, paintHistogram)
        canvas?.drawText("15", 180f, 1780f, paintHistogram)
        canvas?.drawText("30", 330f, 1780f, paintHistogram)
        canvas?.drawText("45", 480f, 1780f, paintHistogram)
        canvas?.drawText("60", 630f, 1780f, paintHistogram)

        //直方图-矩形部分
        canvas?.drawRect(51f,1653f,199f,1749f,paintRect)
        canvas?.drawRect(201f,1700f,349f,1749f,paintRect)
        canvas?.drawRect(351f,1500f,499f,1749f,paintRect)
        canvas?.drawRect(501f,1293f,649f,1749f,paintRect)
    }

    private fun pie(canvas: Canvas?){
        val paintPieGR = Paint(Paint.ANTI_ALIAS_FLAG)
        paintPieGR.color = Color.GRAY

        val paintPieGre = Paint(Paint.ANTI_ALIAS_FLAG)
        paintPieGre.color = Color.GREEN

        val paintPieBLU = Paint(Paint.ANTI_ALIAS_FLAG)
        paintPieBLU.color = Color.BLUE

        val paintPieMAG = Paint(Paint.ANTI_ALIAS_FLAG)
        paintPieMAG.color = Color.MAGENTA

        val paintPieRED = Paint(Paint.ANTI_ALIAS_FLAG)
        paintPieRED.color = Color.RED

        val paintDis = Paint(Paint.ANTI_ALIAS_FLAG)
        paintDis.textSize = 30f

        canvas?.drawArc(750f,1090f,950f,1300f,-90f,30f,true,paintPieGR)
        canvas?.drawArc(750f,1090f,950f,1300f,21f,-75f,true,paintPieGre)
        canvas?.drawArc(750f,1100f,950f,1300f,22f,40f,true,paintPieBLU)
        canvas?.drawArc(745f,1100f,950f,1300f,63f,90f,true,paintPieMAG)
        canvas?.drawArc(740f,1095f,940f,1295f,155f,110f,true,paintPieRED)

        canvas?.drawLine(650f,1150f,750f,1150f,paintDis)
        canvas?.drawText("RED",600f,1150f,paintDis)

        canvas?.drawLine(750f,1350f,800f,1290f,paintDis)
        canvas?.drawText("MAGENTA",700f,1380f,paintDis)

        canvas?.drawLine(970f,1320f,925f,1270f,paintDis)
        canvas?.drawText("BLUE",950f,1340f,paintDis)

        canvas?.drawLine(950f,1190f,1000f,1170f,paintDis)
        canvas?.drawText("GREEN",975f,1150f,paintDis)

        canvas?.drawLine(870f,1093f,890f,1040f,paintDis)
        canvas?.drawText("GRAY",870f,1020f,paintDis)
    }

}