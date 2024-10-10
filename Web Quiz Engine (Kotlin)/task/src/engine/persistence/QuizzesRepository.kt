package engine.persistence

import engine.models.Answer
import engine.models.AnswerRequest
import engine.models.Quiz
import engine.models.QuizResponse

interface QuizzesRepository {
    fun findById(id: Long): QuizResponse
    fun findAll(): List<QuizResponse>
    fun save(quiz: Quiz): QuizResponse
    fun solveById(id: Long, req: AnswerRequest): Answer
}
