package api.agendamentopacientes.user.service

import api.agendamentopacientes.exception.BadRequestException
import api.agendamentopacientes.exception.NotFoundException
import api.agendamentopacientes.exception.enum.Errors
import api.agendamentopacientes.user.entity.UserModel
import api.agendamentopacientes.user.enum.Role
import api.agendamentopacientes.user.repository.UserRepository
import api.agendamentopacientes.user.enum.UserStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserServiceImpl : UserService {


    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var bCrypt: BCryptPasswordEncoder


    override fun create(userModel: UserModel) {
        val emailSaved = userRepository.existsByEmail(userModel.email)
        if (emailSaved) {
            throw BadRequestException(Errors.PA103.message.format(userModel.status), Errors.PA103.code)
        }
        userModel.roles = setOf(Role.ROLE_USER)
        userModel.password = bCrypt.encode(userModel.password)
        userRepository.save(userModel)

    }


    override fun getById(id: Long): UserModel {
        return userRepository.findById(id)
            .orElseThrow { NotFoundException(Errors.PA101.message.format(id), Errors.PA101.code) }
    }


    override fun update(userModel: UserModel, id: Long) {
        val userSaved = getById(id)
        if (userSaved.status == UserStatus.INACTIVE) {
            throw BadRequestException(Errors.PA102.message.format(userModel.status), Errors.PA102.code)
        }
        if (userModel.email !== null) {
            if (existsEmail(userModel.email)) {
                val userEmail = userByEmail(userModel.email)
                if (userEmail.get().id !== userSaved.id) {
                    throw BadRequestException(Errors.PA103.message.format(userModel.status), Errors.PA103.code)
                }
            }
            userRepository.save(userModel)
        }
        userRepository.save(userModel)
    }


    override fun delete(id: Long) {
        val userSaved = getById(id)
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





