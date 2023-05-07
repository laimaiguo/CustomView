package com.example.customview.draw

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import com.example.customview.R
import kotlinx.android.synthetic.main.titlelayout.view.*

/**
 *Create by GWJ 2023/3/19 18:20
 **/
class TitleLayout(context: Context,attr:AttributeSet):LinearLayout(context,attr) {
    init {
        LayoutInflater.from(context).inflate(R.layout.titlelayout,this)
        backBtn.setOnClickListener {
            val activity = context as Activity
            activity.finish()
        }
        editBtn.setOnClickListener {
            Toast.makeText(context, "You click editBtn", Toast.LENGTH_SHORT).show()
        }
    }

}