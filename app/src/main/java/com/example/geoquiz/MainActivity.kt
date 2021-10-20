package com.example.geoquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

private const val TAG= "MainActivity"
private const val KEY_INDEX= "index"

class MainActivity : AppCompatActivity() {
    private var countCorrectAnswer= 0

    // Использование lazy допускает применение свойства quizViewModel как val, а не var
    // расчет и назначение quizViewModel не будет происходить, пока не запросим доступ к quizViewModel впервые
    private val quizViewModel : QuizViewModel by lazy {

        //Связь Activity  с экземпляром QuizViewModel
        //ViewModelProviders предоставляет экземпляры класса ViewModelProvider
        //Вызов ViewModelProviders.of(this) создает и возвращает ViewModelProvider связанный с Activity

        //ViewModelProvider передает Activity экземпляр ViewModel
        //provider.get(QuizViewModel::class.java) возвразает QuizViewModel


        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    override fun onResume() {
        super.onResume()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentIndex = savedInstanceState?.getInt(KEY_INDEX) ?: 0
        quizViewModel.currentIndex = currentIndex


        true_button.setOnClickListener {
            checkAnswer(true)
        }

        false_button.setOnClickListener {
            checkAnswer(false)
        }

        next_button.setOnClickListener {
                quizViewModel.moveToNext()
                updateQuestion()


            true_button.isEnabled = true
            false_button.isEnabled = true
        }


        prev_button.setOnClickListener {
            quizViewModel.moveToPrev()
            updateQuestion()
        }


        cheat_button.setOnClickListener {
            val answerIsTrue = quizViewModel.currentQuestionAnswer

            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            startActivity(intent)
        }


        question_text_view.setOnClickListener{

            quizViewModel.moveToNext()
            updateQuestion()


            true_button.isEnabled = true
            false_button.isEnabled = true
        }

        updateQuestion()
    }

    private fun updateQuestion() {

        val questionTextResId = quizViewModel.currentQuestionText
        question_text_view.setText(questionTextResId)
    }

    private fun checkAnswer( userAnswer: Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer
        if ( userAnswer == correctAnswer) {
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
            countCorrectAnswer++
        }
        else{
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
        true_button.isEnabled = false
        false_button.isEnabled = false
    }
}