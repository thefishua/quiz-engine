package engine.service

import engine.models.User
import engine.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService(
    var userRepository: UserRepository,
    var passwordEncoder: PasswordEncoder,
) {
    fun registerNewUser(user: User) {
        if (userRepository.existsById(user.email)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "user is already created")
        }
        userRepository.save(User(
            email = user.email,
            password = passwordEncoder.encode(user.password),
            authority = user.authority,
        ))
    }
}