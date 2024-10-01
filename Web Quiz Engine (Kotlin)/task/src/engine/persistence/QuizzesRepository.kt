package engine.persistence

import engine.models.Quiz
import engine.models.QuizResponse

interface QuizzesRepository {
    fun findById(id: Int): QuizResponse
//    fun findAll(pageable: Pageable): Page<Quiz>
    fun save(quiz: Quiz): QuizResponse
}
