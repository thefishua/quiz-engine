package engine.service

import com.sun.tools.javac.jvm.ByteCodes.ret
import engine.models.Answer
import engine.models.AnswerRequest
import engine.models.FAILURE
import engine.models.Quiz
import engine.models.QuizResponse
import engine.models.SUCCESS
import engine.models.User
import engine.repository.QuizzesRepository
import engine.security.UserPrincipal
import java.awt.SystemColor.text
import jdk.internal.org.jline.utils.Colors.s
import org.hibernate.query.Page.page
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class QuizService(private val quizzesRepository: QuizzesRepository) {
    fun add(quiz: Quiz): Quiz {
        return quizzesRepository.save(quiz)

    }
    fun findById(id: Long): Quiz {
        return quizzesRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "quiz not found") }
    }
    fun findAll(pageNumber: Int, pageSize: Int): Page<Quiz> {
        val paging = PageRequest.of(pageNumber, pageSize)
        return quizzesRepository.findAll(paging)
    }
    fun solveById(id: Long, req: AnswerRequest): Answer {
        val quiz = quizzesRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "quiz not found") }
        return if (req.answer == quiz.answer) Answer(
            success = true,
            feedback = SUCCESS
        ) else Answer(
            success = false,
            feedback = FAILURE
        )
    }
    fun delete(id: Long, user: User) {
        val quiz = quizzesRepository.findById(id).orElseThrow() { ResponseStatusException(HttpStatus.NOT_FOUND, "quiz not found") }
        if (quiz.user.email == user.email) {
            quizzesRepository.deleteById(id)
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "user not the same")
        }
    }
}