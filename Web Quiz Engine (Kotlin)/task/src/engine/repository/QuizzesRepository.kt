package engine.repository

import engine.models.Quiz
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface QuizzesRepository: PagingAndSortingRepository<Quiz, Long>, CrudRepository<Quiz, Long>
