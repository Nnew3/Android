package com.example.mz_focusnews.feature.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mz_focusnews.core.api.model.Question
import com.example.mz_focusnews.core.api.model.QuizScoreRequest
import com.example.mz_focusnews.core.api.model.QuizUserInfo
import com.example.mz_focusnews.core.api.model.Ranking
import com.example.mz_focusnews.core.api.service.ApiService
import com.example.mz_focusnews.core.api.service.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel() {
    private val service: ApiService = RetrofitClient.getInstance().create(ApiService::class.java)

    private val _quizInfoState = MutableStateFlow(QuizInfoState())
    val quizInfoState: StateFlow<QuizInfoState> = _quizInfoState

    private val _rankingState = MutableStateFlow(RankingState())
    val rankingState: StateFlow<RankingState> = _rankingState

    private val _questionState = MutableStateFlow(QuestionState())
    val questionState: StateFlow<QuestionState> = _questionState

    private val _scoreState = MutableStateFlow(ScoreState())

    init {
        fetchQuizUserInfo()
        fetchQuizRanking()
    }

    fun fetchQuizUserInfo() {
        _quizInfoState.value = QuizInfoState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = service.getQuizUserInfo()
                _quizInfoState.value =
                    QuizInfoState(quizUserInfo = response.data, isLoading = false)
                Log.d("Quiz", "fetchQuizInfo called:: ${_quizInfoState.value}")
            } catch (e: Exception) {
                _quizInfoState.value =
                    QuizInfoState(isLoading = false, isError = true, errorMsg = e.message)
                Log.e("Quiz", "Quiz Info Error: ${_quizInfoState.value.errorMsg}")
            }
        }
    }

    fun fetchQuizRanking() {
        _rankingState.value = RankingState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = service.getQuizRanking()
                _rankingState.value = RankingState(ranking = response.data, isLoading = false)
                Log.d("Quiz", "fetchQuizRanking called:: ${_rankingState.value}")
            } catch (e: Exception) {
                _rankingState.value =
                    RankingState(isLoading = false, isError = true, errorMsg = e.message)
                Log.e("Quiz", "Ranking Error: ${_rankingState.value.errorMsg}")
            }
        }
    }

    fun fetchQuestion(num: Int) {
        _questionState.value = QuestionState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = service.getQuizQuestions(num)
                _questionState.value = QuestionState(question = response.data, isLoading = false)
                Log.d("Quiz", "fetchQuestion[$num] called:: ${_questionState.value}")
            } catch (e: Exception) {
                _questionState.value =
                    QuestionState(isLoading = false, isError = true, errorMsg = e.message)
                Log.e("Quiz", "Question Error: ${_questionState.value.errorMsg}")
            }
        }
    }

    fun sendTotalScore(userId:Long, totalScore: Int){
        _scoreState.value = ScoreState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = service.setQuizScore(QuizScoreRequest(userId, totalScore))
                _scoreState.value = ScoreState(isLoading = false)
                Log.d("Quiz", "sendTotalScore called ($userId, $totalScore):: ${_scoreState.value}")
            } catch (e: Exception) {
                _scoreState.value = ScoreState(isLoading = false, isError = true, errorMsg = e.message)
                Log.e("Quiz", "Score Error: ${_scoreState.value.errorMsg}")
            }
        }

    }
}

data class QuizInfoState(
    val quizUserInfo: QuizUserInfo? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMsg: String? = null
)

data class RankingState(
    val ranking: Ranking? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMsg: String? = null
)

data class QuestionState(
    val question: Question? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMsg: String? = null
)

data class ScoreState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMsg: String? = null
)