package engine.api.controllers

import engine.models.Answer
import engine.models.AnswerRequest
import engine.models.Completed
import engine.models.CompletedResponse
import engine.models.Quiz
import engine.models.QuizRequest
import engine.models.QuizResponse
import engine.models.UserRequest
import engine.security.UserPrincipal
import jakarta.validation.Valid
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
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
    fun create(@Valid @RequestBody quiz: QuizRequest, @AuthenticationPrincipal userPrincipal: UserPrincipal): QuizResponse

    @GetMapping("/quizzes/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: Long): QuizResponse

    @GetMapping("/quizzes")
    @ResponseStatus(HttpStatus.OK)
    fun findAll(
        @RequestParam(defaultValue = "0") pageNumber: @Min(0) Int,
        @RequestParam(defaultValue = "10") pageSize: @Min(10) @Max(30) Int,
    ): Page<QuizResponse>

    @PostMapping("/quizzes/{id}/solve")
    @ResponseStatus(HttpStatus.OK)
    fun solveById(@PathVariable id: Long, @RequestBody req: AnswerRequest, @AuthenticationPrincipal userPrincipal: UserPrincipal): Answer

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    fun registerNewUser(@Valid @RequestBody user: UserRequest)

    @DeleteMapping("/quizzes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long, @AuthenticationPrincipal userPrincipal: UserPrincipal)

    @GetMapping("/quizzes/completed")
    @ResponseStatus(HttpStatus.OK)
    fun completed(
        @RequestParam(defaultValue = "0") page: @Min(0) Int,
        @RequestParam(defaultValue = "10") pageSize: @Min(10) @Max(30) Int,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
    ): Page<CompletedResponse>
}