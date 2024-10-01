package engine.models

object QuizId {
    private var id = 1
    fun getNextInt(): Int = id++
}

data class Quiz (
    val id: Int,
    val title: String,
    val text: String,
    val options: List<String>,
    val answer: Int,
)

data class QuizRequest (
    val title: String,
    val text: String,
    val options: List<String>,
    val answer: Int,
)

data class QuizResponse (
    val id: Int,
    val title: String,
    val text: String,
    val options: List<String>,
)