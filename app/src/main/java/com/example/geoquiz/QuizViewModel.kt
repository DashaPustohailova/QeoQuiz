package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.ArrayList
import kotlin.collections.arrayListOf as arrayListOf

private const val TAG= "QuizViewModel"

class QuizViewModel : ViewModel(){

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true)
    )

    var currentIndex = 0
    var cheatArray : ArrayList<Int> = arrayListOf()

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext(){
        currentIndex = (currentIndex + 1)
        if(currentIndex == questionBank.size)
            currentIndex = 0
    }

    fun moveToPrev(){
        currentIndex =
            if(currentIndex == 0 )
                0
            else
                currentIndex - 1
    }

    fun addCheat(cheatIndex : Int){
        cheatArray.add(cheatIndex)
    }
}