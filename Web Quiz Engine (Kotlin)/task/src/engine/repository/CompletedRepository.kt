package engine.repository

import engine.models.Completed
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CompletedRepository: PagingAndSortingRepository<Completed, Long>, CrudRepository<Completed, Long> {
    @Query(value = "SELECT c FROM Completed c WHERE c.userId = :email")
    fun findAllByUserId(@Param("email") email: String, pageable: Pageable): Page<Completed>
}