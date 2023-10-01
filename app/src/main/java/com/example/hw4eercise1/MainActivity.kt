package com.example.hw4eercise1

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.hw4eercise1.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar





//Naming the Tag as 'MainActivty'
private const val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {



//Giving a name to my ViewModel that will be referenced from another page later
    private val quizViewModel: QuizViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



//Binding and layout Inflator stayed the same as the last few activities
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//Logs that name and define output in the LogCat
        Log.d(TAG, "onCreate (Bundle?) called")
        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")



        binding.trueButton.setOnClickListener {

            checkAnswer(true)
            binding.trueButton.isEnabled = !(binding.trueButton.isEnabled)
            binding.falseButton.isEnabled = !(binding.falseButton.isEnabled)

        }




        binding.falseButton.setOnClickListener {

            checkAnswer(false)
            binding.falseButton.isEnabled = !(binding.falseButton.isEnabled)
            binding.trueButton.isEnabled = !(binding.trueButton.isEnabled)

        }




        binding.questionTextView.setOnClickListener {


         quizViewModel.moveNext()

        }



//Previous button that is not going to be used in this activity
//        binding.previousButton.setOnClickListener {
//
//
//            quizViewModel.movePrevious()
//
//        }




        binding.nextButton.setOnClickListener {



            binding.falseButton.isEnabled = true
            binding.trueButton.isEnabled = true

//New method of moving forward a question
//Defined in QuizViewModel.kt file
            quizViewModel.moveNext()


            updateQuestion()


        }

        updateQuestion()

    }





//Update question function
    private fun updateQuestion() {

        val questionTextResId = quizViewModel.currentQuestionText

        binding.questionTextView.setText(questionTextResId)

    }




//Function to check if user answer is correct
    private fun checkAnswer(userAnswer: Boolean) {


//New way of checking if user answer is correct using QuizViewModel.kt
        val correctAnswer = quizViewModel.currentQuestionAnswer


        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast

        } else {
            R.string.incorrect_toast
        }


    }


//Logs for the first exercise
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }


    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }



    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }



    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }



    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

}

