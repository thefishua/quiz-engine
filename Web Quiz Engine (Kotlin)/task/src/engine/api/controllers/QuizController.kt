package engine.api.controllers

import engine.models.Answer
import engine.models.FAILURE
import engine.models.Quiz
import engine.models.SUCCESS
import org.springframework.web.bind.annotation.RestController

@RestController
class QuizController: QuizApi {
    override fun getQuiz(): Quiz {
        return Quiz(
            id = 1,
            title = "The Java Logo",
            text = "What is depicted on the Java logo?",
            options = listOf("Robot", "Tea leaf", "Cup of coffee", "Bug"),
            answer = 2
        )
    }

    override fun answerQuiz(answer: Int): Answer {
        if (answer == 2) {
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
}