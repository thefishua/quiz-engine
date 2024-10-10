package engine.persistence

import engine.models.Answer
import engine.models.AnswerRequest
import engine.models.FAILURE
import engine.models.Quiz
import engine.models.QuizResponse
import engine.models.SUCCESS
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository
import org.springframework.web.server.ResponseStatusException

@Repository
class InMemoryQuizzesRepository(
    private val quizzes: MutableMap<Long, Quiz> = mutableMapOf()
): MutableMap<Long, Quiz> by quizzes {
    fun findById(id: Long): QuizResponse {
        val quiz = quizzes[id] ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "quiz not found")
        return QuizResponse(
            id = quiz.id,
            title = quiz.title?: "",
            text = quiz.text?: "",
            options = quiz.options?: listOf(),
        )
    }

    fun save(quiz: Quiz): QuizResponse {
        quizzes[quiz.id] = quiz
        return QuizResponse(
            id = quiz.id,
            title = quiz.title?: "",
            text = quiz.text?: "",
            options = quiz.options?: listOf(),
        )
    }

    fun findAll(): List<QuizResponse> {
        return quizzes.values.map { quiz ->
            QuizResponse(
                id = quiz.id,
                title = quiz.title?: "",
                text = quiz.text?: "",
                options = quiz.options?: listOf(),
            )
        }
    }

    fun solveById(id: Long, req: AnswerRequest): Answer {
        val quiz = quizzes[id] ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "quiz not found")
        return if (req.answer == quiz.answer) Answer(
            success = true,
            feedback = SUCCESS
        ) else Answer(
            success = false,
            feedback = FAILURE
        )
    }
}