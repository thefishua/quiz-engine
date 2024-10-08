package engine.api.controllers

import engine.models.Answer
import engine.models.AnswerRequest
import engine.models.Quiz
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus

@RequestMapping("/api/quiz")
interface QuizApi {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getQuiz(): Quiz
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun answerQuiz(@RequestBody req: AnswerRequest): Answer
}