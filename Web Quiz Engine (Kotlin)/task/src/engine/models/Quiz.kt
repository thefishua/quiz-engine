package engine.models

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

object QuizId {
    private var id = 1
    fun getNextInt(): Int = id++
}

data class Quiz (
    val id: Int,
    val title: String,
    val text: String,
    val options: List<String>,
    val answer: List<Int>,
)

data class QuizRequest (
    @field:NotBlank
    val title: String,
    @field:NotBlank
    val text: String,
    @field:Size(min = 2)
    val options: List<String>,
    val answer: List<Int> = listOf(),
)

data class QuizResponse (
    val id: Int,
    val title: String,
    val text: String,
    val options: List<String>,
)