package com.example.customview

import kotlin.jvm.Volatile as Volatile1

/**
 *Author GWJ
 *2022/2/9 16:59
 **/
fun main() {
    fun test () {
         var flag = false
        Thread(){
            run {
                println("Thread1--start")
                while (!flag){
                }
                println("Thread1--end")
            }
        }.start()
        Thread.sleep(100)
        Thread(){
            run {
                println("Thread2--start")
                flag=true
                println("Thread2--end")
            }
        }.start()
    }
    test()
    //do not ask me why I want to sleep.
    // I do not know why I want to sleep.
}