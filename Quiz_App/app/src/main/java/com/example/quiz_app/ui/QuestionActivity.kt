package com.example.quiz_app.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.quiz_app.R
import com.example.quiz_app.data.Question
import com.example.quiz_app.data.SetData
import com.example.quiz_app.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity() {



    private var currentPosition: Int = 1
    private var questionList: ArrayList<Question>? = null
    private var selectedOption: Int = 0

    private lateinit var binding: ActivityQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        questionList = SetData.getQuestion()


        setQuestion()

        selectOption()

        submitButton()


    }

    private fun submitButton() {
        binding.submit.setOnClickListener {
            if (selectedOption == 0) {
                currentPosition++

                when {
                    currentPosition <= questionList!!.size -> {
                        setQuestion()
                    }
                    else -> {
                        Toast.makeText(
                            this,
                            "You have successfully completed the Quiz", Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            } else {
                val question = questionList?.get(currentPosition - 1)
                if (question!!.correct_ans != selectedOption) {
                    setColor(selectedOption, R.drawable.wrong_question_option)
                }
                setColor(question.correct_ans, R.drawable.correct_question_option)
                if (currentPosition == questionList!!.size) {
                    binding.submit.text = "Finish"
                } else {
                    binding.submit.text = "Go to next question"
                }
                selectedOption = 0
            }
        }
    }
    private fun selectOption() {
        binding.opt1.setOnClickListener {

            selectedOptionStyle(binding.opt1, 1)
        }
        binding.opt2.setOnClickListener {

            selectedOptionStyle(binding.opt2, 2)
        }
        binding.opt3.setOnClickListener {

            selectedOptionStyle(binding.opt3, 3)
        }
        binding.opt4.setOnClickListener {

            selectedOptionStyle(binding.opt4, 4)
        }
    }
    private fun setColor(opt: Int, color: Int) {
        when (opt) {
            1 -> {
                binding.opt1.background = ContextCompat.getDrawable(this, color)
            }

            2 -> {
                binding.opt2.background = ContextCompat.getDrawable(this, color)
            }

            3 -> {
                binding.opt3.background = ContextCompat.getDrawable(this, color)
            }

            4 -> {
                binding.opt4.background = ContextCompat.getDrawable(this, color)
            }
        }
    }
    private fun setQuestion() {

        setOptionStyle()
        val question = questionList!![currentPosition - 1]

        if (currentPosition == questionList!!.size) {
            binding.submit.text = "FINISH"
        } else {
            binding.submit.text = "Go to Next"
        }

        binding.questionText.text = question.question
        binding.opt1.text = question.option_one
        binding.opt2.text = question.option_tw0
        binding.opt3.text = question.option_three
        binding.opt4.text = question.option_four
        binding.progressText.text = "${currentPosition}" + " / " + "${questionList!!.size}"
        binding.progressBar.progress = currentPosition
        binding.progressBar.max = questionList!!.size

    }
    private fun setOptionStyle() {
        val optionList = ArrayList<TextView>()
        optionList.add(0, binding.opt1)
        optionList.add(1, binding.opt2)
        optionList.add(2, binding.opt3)
        optionList.add(3, binding.opt4)

        for (op in optionList) {
            op.setTextColor(Color.parseColor(/* colorString = */ "#555151"))
            op.background = ContextCompat.getDrawable(this,
                R.drawable.question_option)
            op.typeface = Typeface.DEFAULT
        }
    }
    private fun selectedOptionStyle(view: TextView, opt: Int) {

        setOptionStyle()
        selectedOption = opt
        view.background = ContextCompat.getDrawable(this, R.drawable.selected_question_option)
        view.typeface = Typeface.DEFAULT_BOLD
        view.setTextColor(Color.parseColor("#000000"))

    }
}