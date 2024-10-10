package engine.models

import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

object QuizId {
    private var id: Long = 1
    fun getNextInt(): Long = id++
}
@Entity
@Table(name = "quizdb")
data class Quiz (
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long = 0,
    val title: String? = null,
    val text: String? = null,
    @ElementCollection
    val options: List<String>? = null,
    @ElementCollection
    val answer: List<Int> = listOf(),
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
    val id: Long,
    val title: String,
    val text: String,
    val options: List<String>,
)