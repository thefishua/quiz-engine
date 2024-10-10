package engine.api.controllers

import engine.models.Answer
import engine.models.AnswerRequest
import engine.models.Quiz
import engine.models.QuizRequest
import engine.models.QuizResponse
import engine.repository.QuizzesRepository
import engine.service.QuizService
import org.springframework.web.bind.annotation.RestController

@RestController
class QuizzesController(
    val quizzesRepository: QuizzesRepository,
    private val quizService: QuizService,
): QuizzesApi {
    override fun create(quiz: QuizRequest): QuizResponse {
        return quizService.add(
            Quiz(
                title = quiz.title,
                text = quiz.text,
                options = quiz.options,
                answer = quiz.answer,
            )
        )
    }

    override fun findById(id: Long): QuizResponse {
        return quizService.findById(id)
    }

    override fun findAll(): List<QuizResponse> {
        return quizService.findAll()
    }

    override fun solveById(id: Long, req: AnswerRequest): Answer {
        return quizService.solveById(id, req)
    }
}