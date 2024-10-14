package engine.api.controllers

import engine.models.Answer
import engine.models.AnswerRequest
import engine.models.FAILURE
import engine.models.Quiz
import engine.models.QuizRequest
import engine.models.QuizResponse
import engine.models.SUCCESS
import engine.models.User
import engine.models.UserRequest
import engine.security.Authority
import engine.security.UserPrincipal
import engine.service.QuizService
import engine.service.UserService
import org.springframework.web.bind.annotation.RestController

@RestController
class QuizController(
    private val userService: UserService,
    private val quizService: QuizService
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
        return quizService.add(
            Quiz(
                title = quiz.title,
                text = quiz.text,
                options = quiz.options,
                answer = quiz.answer,
                user = userPrincipal.user
            )
        )
    }

    override fun findById(id: Long): QuizResponse {
        return quizService.findById(id)
    }

    override fun findAll(): List<QuizResponse> {
        return quizService.findAll()
    }

    override fun solveById(id: Long, req: AnswerRequest): Answer {
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
}