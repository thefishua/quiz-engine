package engine.service

import engine.models.Answer
import engine.models.AnswerRequest
import engine.models.FAILURE
import engine.models.Quiz
import engine.models.QuizResponse
import engine.models.SUCCESS
import engine.models.User
import engine.repository.QuizzesRepository
import engine.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class QuizService(private val quizzesRepository: QuizzesRepository) {
    fun add(quiz: Quiz): QuizResponse {
        val quizResponse = quizzesRepository.save(quiz)
        return QuizResponse(
            id = quizResponse.id,
            title = quizResponse.title?: "",
            text = quizResponse.text?: "",
            options = quizResponse.options?: listOf(),
        )
    }
    fun findById(id: Long): QuizResponse {
        val quizResponse = quizzesRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "quiz not found") }
        return QuizResponse(
            id = quizResponse.id,
            title = quizResponse.title?: "",
            text = quizResponse.text?: "",
            options = quizResponse.options?: listOf(),
        )
    }
    fun findAll(): List<QuizResponse> {
        val quizResponse = quizzesRepository.findAll()
        return quizResponse.map { quiz ->
            QuizResponse(
                id = quiz.id,
                title = quiz.title?: "",
                text = quiz.text?: "",
                options = quiz.options?: listOf(),
            )
        }
    }
    fun solveById(id: Long, req: AnswerRequest): Answer {
        val quiz = quizzesRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "quiz not found") }
        return if (req.answer == quiz.answer) Answer(
            success = true,
            feedback = SUCCESS
        ) else Answer(
            success = false,
            feedback = FAILURE
        )
    }
    fun delete(id: Long, user: User) {
        val quiz = quizzesRepository.findById(id).orElseThrow() { ResponseStatusException(HttpStatus.NOT_FOUND, "quiz not found") }
        if (quiz.user == user) {
            quizzesRepository.deleteById(id)
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "user not the same")
        }
    }
}