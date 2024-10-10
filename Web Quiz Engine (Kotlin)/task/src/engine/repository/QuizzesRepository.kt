package engine.repository

import engine.models.Quiz
import org.springframework.data.repository.CrudRepository

interface QuizzesRepository: CrudRepository<Quiz, Long> {}

