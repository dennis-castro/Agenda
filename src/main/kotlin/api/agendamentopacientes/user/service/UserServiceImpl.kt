package api.agendamentopacientes.user.service

import api.agendamentopacientes.exception.BadRequestException
import api.agendamentopacientes.exception.NotFoundException
import api.agendamentopacientes.exception.enum.Errors
import api.agendamentopacientes.user.entity.UserModel
import api.agendamentopacientes.user.repository.UserRepository
import api.agendamentopacientes.user.enum.UserStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserServiceImpl : UserService {


    @Autowired
    lateinit var userRepository: UserRepository


    override fun create(userRequest: UserModel) {
        val emailSaved = userRepository.existsByEmail(userRequest.email)
        if (emailSaved) {
            throw BadRequestException(Errors.PA103.message.format(userRequest.status), Errors.PA103.code)
        }
        userRepository.save(userRequest)

    }


    override fun userById(id: Long): UserModel {
        return userRepository.findById(id)
            .orElseThrow { NotFoundException(Errors.PA101.message.format(id), Errors.PA101.code) }
    }


    override fun update(userRequest: UserModel, id: Long) {
        val userSaved = userById(id)
        if (userSaved.status == UserStatus.INACTIVE) {
            throw BadRequestException(Errors.PA102.message.format(userRequest.status), Errors.PA102.code)
        }
        if (userRequest.email !== null) {
            if (existsEmail(userRequest.email)) {
                val userEmail = userByEmail(userRequest.email)
                if (userEmail.get().id !== userSaved.id) {
                    throw BadRequestException(Errors.PA103.message.format(userRequest.status), Errors.PA103.code)
                }
            }
            userRepository.save(userRequest)
        }
        userRepository.save(userRequest)
    }


    override fun delete(id: Long) {
        val userSaved = userById(id)
        userSaved.status = UserStatus.INACTIVE
        userRepository.save(userSaved)
    }

    override fun existsEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    override fun userByEmail(email: String): Optional<UserModel> {
        return userRepository.findByEmail(email)
    }


}





