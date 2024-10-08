package engine.api.controllers

import engine.models.Answer
import engine.models.AnswerRequest
import engine.models.QuizRequest
import engine.models.QuizResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus

@RequestMapping("/api/quizzes")
interface QuizzesApi {
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    fun create(@RequestBody quiz: @Valid QuizRequest): QuizResponse

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: Long): QuizResponse

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): List<QuizResponse>

    @PostMapping("/{id}/solve")
    @ResponseStatus(HttpStatus.OK)
    fun solveById(@PathVariable id: Long, @RequestBody req: AnswerRequest): Answer
}