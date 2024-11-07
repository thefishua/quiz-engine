package engine.api.controllers

import engine.models.Answer
import engine.models.AnswerRequest
import engine.models.Completed
import engine.models.CompletedResponse
import engine.models.FAILURE
import engine.models.Quiz
import engine.models.QuizRequest
import engine.models.QuizResponse
import engine.models.SUCCESS
import engine.models.User
import engine.models.UserRequest
import engine.security.Authority
import engine.security.UserPrincipal
import engine.service.CompletedService
import engine.service.QuizService
import engine.service.UserService
import java.time.LocalDateTime
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.RestController

@RestController
class QuizController(
    private val userService: UserService,
    private val quizService: QuizService,
    private val completedService: CompletedService,
): QuizApi {
    override fun getQuiz(): QuizResponse {
        return QuizResponse(
            id = 1,
            title = "The Java Logo",
            text = "What is depicted on the Java logo?",
            options = listOf("Robot", "Tea leaf", "Cup of coffee", "Bug"),
        )
    }

    override fun answerQuiz(req: AnswerRequest): Answer {
        if (req.answer == listOf(2)) {
            return Answer(
                success = true,
                feedback = SUCCESS
            )
        }
        return Answer(
            success = false,
            feedback = FAILURE
        )
    }

    override fun create(quiz: QuizRequest, userPrincipal: UserPrincipal): QuizResponse {
        val quizResponse = quizService.add(
            Quiz(
                title = quiz.title,
                text = quiz.text,
                options = quiz.options,
                answer = quiz.answer,
                user = userPrincipal.user
            )
        )
        return QuizResponse(
            id = quizResponse.id,
            title = quizResponse.title?: "",
            text = quizResponse.text?: "",
            options = quizResponse.options?: listOf(),
        )
    }

    override fun findById(id: Long): QuizResponse {
        val quizResponse = quizService.findById(id)
        return QuizResponse(
            id = quizResponse.id,
            title = quizResponse.title?: "",
            text = quizResponse.text?: "",
            options = quizResponse.options?: listOf(),
        )
    }

    override fun findAll(page: Int, pageSize: Int): Page<QuizResponse> {
        val quizPages = quizService.findAll(page, pageSize)
        return quizPages.map { quiz ->
            QuizResponse(
                id = quiz.id,
                title = requireNotNull(quiz.title),
                text = requireNotNull(quiz.text),
                options = requireNotNull(quiz.options),
            )
        }
    }

    override fun solveById(id: Long, req: AnswerRequest, userPrincipal: UserPrincipal): Answer {
        val answer = quizService.solveById(id, req)
        if (answer.success) {
            val quiz = quizService.findById(id)
            completedService.add(completed = Completed(
                quizId = quiz.id,
                userId = userPrincipal.user.email,
                completedAt = LocalDateTime.now(),
                user = userPrincipal.user,
                quiz = quiz,
            ))
        }
        return quizService.solveById(id, req)
    }

    override fun registerNewUser(user: UserRequest) {
        userService.registerNewUser(User(
            email = user.email,
            password = user.password,
            authority = Authority.USER,
        ))
    }

    override fun delete(id: Long, userPrincipal: UserPrincipal) {
        quizService.delete(id, userPrincipal.user)
    }

    override fun completed(page: Int, pageSize: Int, userPrincipal: UserPrincipal): Page<CompletedResponse> {
        val completedPages = completedService.completed(page, pageSize, userPrincipal)
        return completedPages.map { completed ->
            CompletedResponse(
                quizId = completed.quizId,
                completedAt = completed.completedAt
            )
        }
    }
}