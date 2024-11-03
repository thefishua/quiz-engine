package engine.repository

import engine.models.Quiz
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface QuizzesRepository: CrudRepository<Quiz, Long> {}