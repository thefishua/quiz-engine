package engine.models

import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

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
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    val user: User
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