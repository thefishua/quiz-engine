package engine.api.controllers

import engine.models.QuizRequest
import engine.models.QuizResponse
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
    @ResponseStatus(HttpStatus.CREATED)
    fun createQuiz(@RequestBody quiz: QuizRequest): QuizResponse

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getQuizById(@PathVariable id: Int): QuizResponse
}