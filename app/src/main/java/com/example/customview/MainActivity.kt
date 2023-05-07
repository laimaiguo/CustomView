package com.example.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnticipateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import com.example.customview.draw.MyLinearLayout
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
                    0 -> image.animate().translationX(500f).setInterpolator(OvershootInterpolator()).duration = 500
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
                            .duration = 1000
                        image.animate()
                            .scaleY(1.5f)
                            .setInterpolator(AnticipateInterpolator())
                            .duration = 1000
                    }
                }

            }
        }
    }


}