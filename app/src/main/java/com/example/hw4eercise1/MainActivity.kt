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

//currentIndex = (currentIndex + 1) % questionBank.size

//New method of progressing to next question
//Defined in QuizViewModel.kt file
         quizViewModel.moveNext()

        }




        binding.previousButton.setOnClickListener {

//currentIndex = (currentIndex - 1) % questionBank.size

//New method of moving back a question
//Defined in QuizViewModel.kt file
            quizViewModel.movePrevious()

        }




        binding.nextButton.setOnClickListener {

//currentIndex = (currentIndex + 1) % questionBank.size

            binding.falseButton.isEnabled = true
            binding.trueButton.isEnabled = true

//New method of moving forward a question
//Defined in QuizViewModel.kt file
            quizViewModel.moveNext()

//(New) variable Increment of questionCount,
//            questionCount++

            updateQuestion()


        }

        updateQuestion()





    }



////(New) I have now moved the condition to the updateQuestion function
////Things seem to be working correctly now, thanks for the help professor!
//    private fun showPoints() {
//
//        val percentage = (numberCorrect.toDouble() / questionBank.size) * 100
//        val percentageNew = "%.1f".format(percentage)
//
//
//
////(New) I will change this out when it comes time to turn things In
////There is to long of a delay when testing
////        Toast.makeText(
////            this,
////            "$percentageNew%",
////            Toast.LENGTH_SHORT
////        )   //Showing the toast
////
////            .show()
//
//
//
//
////(New)When If statement is working will display a green Snackbar
//        val snackBar = Snackbar.make (
//            findViewById(android.R.id.content),
//            "$percentageNew%",
//            Snackbar.LENGTH_SHORT
//        )
//
//        snackBar.setTextColor(Color.BLACK)
//        snackBar.setBackgroundTint(Color.GREEN)
//        snackBar.show()
//
//    }









    //Update question function
    private fun updateQuestion() {
//        val questionTextResId = questionBank[currentIndex].textResId
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)



    }












//Function to check if user answer is correct
    private fun checkAnswer(userAnswer: Boolean) {


//val correctAnswer = questionBank[currentIndex].answer


//New way of checking if user answer is correct using QuizViewModel.kt
        val correctAnswer = quizViewModel.currentQuestionAnswer


        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast

        } else {
            R.string.incorrect_toast
        }


    }



////(New) Added 9/19 to take into consideration left out question
//        if (userAnswer == correctAnswer)
//
//
//            numberCorrect++
//
//
//
//        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
//            .show()





























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

