package com.example.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        turn_canvas.setOnClickListener {
            canvas_view.visibility == View.VISIBLE
            path_view.visibility == View.GONE
            Toast.makeText(this,"canvas",Toast.LENGTH_SHORT).show()
        }
        turn_path.setOnClickListener {
            path_view.visibility == View.VISIBLE
            canvas_view.visibility == View.GONE
            Toast.makeText(this,"path",Toast.LENGTH_SHORT).show()

        }
    }
}