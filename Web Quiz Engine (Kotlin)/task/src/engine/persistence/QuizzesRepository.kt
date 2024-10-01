package engine.persistence

import engine.models.Answer
import engine.models.Quiz
import engine.models.QuizResponse

interface QuizzesRepository {
    fun findById(id: Int): QuizResponse
    fun findAll(): List<QuizResponse>
    fun save(quiz: Quiz): QuizResponse
    fun solveById(id: Int, answer: Int): Answer
}
