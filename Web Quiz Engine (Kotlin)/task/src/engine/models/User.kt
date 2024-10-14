package engine.models

import com.fasterxml.jackson.annotation.JsonIgnore
import engine.security.Authority
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
@Table(name = "users")
data class User (
    @Id
    val email: String,
    val password: String,
    @Enumerated(EnumType.STRING)
    var authority: Authority,
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val quizzes: List<Quiz>? = null,
)

data class UserRequest(
    @field:NotNull
    @Email(regexp = "\\w+@\\w+\\.\\w+")
    val email: String,

    @field:NotBlank
    @field:Size(min = 5, max = 255)
    val password: String,
)