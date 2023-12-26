package com.example.quiz_app.data

data class Question(
    val id:Int,
    val question:String,

    val option_one:String,
    val option_tw0:String,
    val option_three:String,
    val option_four:String,
    val correct_ans:Int
)
