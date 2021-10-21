package com.example.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cheat.*

private const val EXTRA_ANSWER_IS_TRUE = "com.example.geoquiz.answer_is_true"
private var answerIsTrue = false
const val EXTRA_ANSWER_SHOWN = "com.example.geoquiz.answer_shown"
const val EXTRA_ANSWER_INDEX = "com.example.geoquiz.answer_index"

class CheatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        show_answer_button.setOnClickListener {
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            answer_text_view.setText(answerText)
            setAnswerShownResult(true)
        }
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    companion object{
        fun newIntent(packageContext: Context, answerIsTrue: Boolean) : Intent {
            return Intent(packageContext, CheatActivity::class.java)
                .putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
//                .putExtra(EXTRA_ANSWER_INDEX ,index)
        }
    }
}