package com.example.customview

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.*
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val mWidth = 2000
        const val mHeight = 2000
        var i = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        /*turn_canvas.setOnClickListener {
            canvas_view.visibility == View.VISIBLE
            path_view.visibility == View.GONE
            Toast.makeText(this,"canvas",Toast.LENGTH_SHORT).show()
        }
        turn_path.setOnClickListener {
            path_view.visibility == View.VISIBLE
            canvas_view.visibility == View.GONE
            Toast.makeText(this,"path",Toast.LENGTH_SHORT).show()

        }*/
        turn_canvas.setOnClickListener(this)
        turn_path.setOnClickListener(this)
        homing.setOnClickListener(this)
        shifting.setOnClickListener(this)
        rotate.setOnClickListener(this)
        scale.setOnClickListener(this)
        gradient.setOnClickListener(this)
        objanimation.setOnClickListener(this)
        i = (0..9).random()
        Log.e("onCreate: ", i.toString())
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.turn_canvas -> Toast.makeText(this, "canvas", Toast.LENGTH_SHORT).show()
            R.id.turn_path -> Toast.makeText(this, "path", Toast.LENGTH_SHORT).show()
            R.id.homing -> {
                this.recreate()
            }
            R.id.shifting -> {
                when (i % 2) {
                    0 -> image.animate().translationX(500f)
                        .setInterpolator(OvershootInterpolator()).duration = 500
                    else -> image.animate().translationY(50f)
                }
            }
            R.id.rotate -> {
                when (i % 2) {
                    0 -> image.animate().rotation(90f).duration = 500
                    else -> image.animate().rotation(-270f).duration = 1000
                }
            }
            R.id.scale -> {
                when (i % 2) {
                    0 -> {
                        image.animate().scaleX(0.5f).duration = 500
                        image.animate().scaleY(0.5f).duration = 500
                    }
                    else -> {
                        image.animate()
                            .scaleX(1.5f)
                            .setInterpolator(AnticipateInterpolator())
                            .withLayer()
                            .duration = 1000
                        image.animate()
                            .scaleY(1.5f)
                            .setInterpolator(AnticipateInterpolator())
                            .duration = 1000
                    }
                }

            }
            R.id.gradient -> {
//                val animation = ObjectAnimator.ofInt(mycircle, "color", Color.GREEN)
//                animation.setEvaluator(HsvEvaluator())
                val animation = ObjectAnimator.ofArgb(view,"color",Color.RED,Color.GREEN)
//                val animation =
//                    ObjectAnimator.ofInt(mycircle, "color", 0xffff0000.toInt(), 0xff00ff00.toInt())
//                animation.setEvaluator(ArgbEvaluator())
                animation.start()
                Toast.makeText(this, "gradient", Toast.LENGTH_SHORT).show()
            }
            R.id.objanimation -> {
                //无效？？？
                val animator1 = ObjectAnimator.ofFloat(view,"scale",0.5f)
                animator1.interpolator = LinearInterpolator()
                val animator2 = ObjectAnimator.ofInt(view,"translation",400)
                animator2.interpolator = DecelerateInterpolator()
                val animatorSet = AnimatorSet()
                animatorSet.playSequentially(animator1,animator2)
                animatorSet.start()
            }
        }
    }


}