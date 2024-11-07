package engine.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "completed")
data class Completed (
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long = 0,
    @Column(name = "QUIZ_ID")
    val quizId: Long = 0,
    @Column(name = "USER_ID")
    @JsonIgnore
    val userId: String? = null,
    val completedAt: LocalDateTime? = null,
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    val user: User,
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "QUIZ_ID", insertable = false, updatable = false)
    val quiz: Quiz,
)

data class CompletedResponse(
    @JsonProperty("id")
    val quizId: Long,
    val completedAt: LocalDateTime?,
)


