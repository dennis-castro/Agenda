package api.agendamentopacientes.user.service

import api.agendamentopacientes.user.entity.UserModel
import java.util.*


interface UserService {

    fun create(userModel: UserModel)

    fun getById(id: Long):UserModel

    fun update(userModel: UserModel,id: Long)

    fun delete(id: Long)

    fun existsEmail(email: String): Boolean

    fun userByEmail(email: String): Optional<UserModel>
}