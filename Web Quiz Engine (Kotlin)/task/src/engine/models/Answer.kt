package engine.models

const val SUCCESS = "Congratulations, you're right!"
const val FAILURE = "Wrong answer! Please, try again."

data class Answer (
    val success: Boolean,
    val feedback: String
)
