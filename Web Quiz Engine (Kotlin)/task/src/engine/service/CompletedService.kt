package engine.service

import engine.models.Completed
import engine.repository.CompletedRepository
import engine.security.UserPrincipal
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class CompletedService(private val completedRepository: CompletedRepository) {
    fun add(completed: Completed) {
        completedRepository.save(completed)
    }
    fun completed(pageNumber: Int, pageSize: Int, userPrincipal: UserPrincipal): Page<Completed> {
        val paging = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Order.desc("completedAt")))
        return completedRepository.findAllByUserId(userPrincipal.user.email, paging)
    }
}