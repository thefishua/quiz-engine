package engine.persistence

import engine.models.Quiz
import engine.models.QuizResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository
import org.springframework.web.server.ResponseStatusException

@Repository
class InMemoryQuizzesRepository(
    private val quizzes: MutableMap<Int, Quiz> = mutableMapOf()
): QuizzesRepository, MutableMap<Int, Quiz> by quizzes {
    override fun findById(id: Int): QuizResponse {
        val quiz = quizzes[id] ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "quiz not found")
        return QuizResponse(
            id = quiz.id,
            title = quiz.title,
            text = quiz.text,
            options = quiz.options,
        )
    }

    override fun save(quiz: Quiz): QuizResponse {
        quizzes[quiz.id] = quiz
        return QuizResponse(
            id = quiz.id,
            title = quiz.title,
            text = quiz.text,
            options = quiz.options,
        )
    }
}