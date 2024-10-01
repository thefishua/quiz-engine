package engine.api.controllers

import engine.models.Answer
import engine.models.Quiz
import engine.models.QuizId
import engine.models.QuizRequest
import engine.models.QuizResponse
import engine.persistence.QuizzesRepository
import org.springframework.web.bind.annotation.RestController

@RestController
class QuizzesController(
    val quizzesRepository: QuizzesRepository,
): QuizzesApi {
    override fun create(quiz: QuizRequest): QuizResponse {
        return quizzesRepository.save(
            Quiz(
                id = QuizId.getNextInt(),
                title = quiz.title,
                text = quiz.text,
                options = quiz.options,
                answer = quiz.answer,
            )
        )
    }

    override fun findById(id: Int): QuizResponse {
        return quizzesRepository.findById(id)
    }

    override fun findAll(): List<QuizResponse> {
        return quizzesRepository.findAll()
    }

    override fun solveById(id: Int, answer: Int): Answer {
        return quizzesRepository.solveById(id, answer)
    }
}