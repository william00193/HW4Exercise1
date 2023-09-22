package com.example.hw4eercise1


import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"


class QuizViewModel(private val savedStateHandle: SavedStateHandle):ViewModel() {

//    init {
//
//        Log.d(TAG, "QuizViewModel instance created")
//
//    }
//
//    override fun onCleared(){
//        super.onCleared()
//
//        Log.d(TAG, "QuizViewModel instance about to be destroyed")
//    }



    private val questionBank = listOf(
        question(R.string.question_australia, true),
        question(R.string.question_oceans, true),
        question(R.string.question_mideast, false),
        question(R.string.question_africa, false),
        question(R.string.question_americas, true),
        question(R.string.question_asia, true),
    )

    private var currentIndex: Int
    get() = savedStateHandle.get(CURRENT_INDEX_KEY)?:0
    set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    //// (New) added a new variable to substitute for currentIndex
// New variable addition seems to be making the condition work for some reason
    private var questionCount = 0

    private var numberCorrect = 0


    val currentQuestionAnswer: Boolean
    get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
    get() = questionBank[currentIndex].textResId

    fun moveNext() {
        currentIndex = (currentIndex + 1 % questionBank.size)
        questionCount++
    }

    fun movePrevious() {
        currentIndex = (currentIndex - 1 % questionBank.size)
    }

}