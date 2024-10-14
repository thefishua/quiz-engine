package engine.api.controllers

import engine.models.Answer
import engine.models.AnswerRequest
import engine.models.Quiz
import engine.models.QuizRequest
import engine.models.QuizResponse
import engine.models.User
import engine.models.UserRequest
import engine.security.UserPrincipal
import jakarta.validation.Valid
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus

@RequestMapping("/api")
interface QuizApi {
    @GetMapping("/quiz")
    @ResponseStatus(HttpStatus.OK)
    fun getQuiz(): QuizResponse

    @PostMapping("/quiz")
    @ResponseStatus(HttpStatus.CREATED)
    fun answerQuiz(@RequestBody req: AnswerRequest): Answer

    @PostMapping("/quizzes")
    @ResponseStatus(HttpStatus.OK)
    fun create(@RequestBody quiz: @Valid QuizRequest, @AuthenticationPrincipal userPrincipal: UserPrincipal): QuizResponse

    @GetMapping("/quizzes/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: Long): QuizResponse

    @GetMapping("/quizzes")
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): List<QuizResponse>

    @PostMapping("/quizzes/{id}/solve")
    @ResponseStatus(HttpStatus.OK)
    fun solveById(@PathVariable id: Long, @RequestBody req: AnswerRequest): Answer

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    fun registerNewUser(@RequestBody user: @Valid UserRequest)

    @DeleteMapping("/quizzes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long, @AuthenticationPrincipal userPrincipal: UserPrincipal)
}