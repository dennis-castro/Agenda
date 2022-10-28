package api.agendamentopacientes.user.service

import api.agendamentopacientes.user.entity.UserModel
import java.util.*


interface UserService {

    fun toSave(userModel: UserModel)

    fun findUserById(id: Long):UserModel

    fun update(userModel: UserModel,id: Long)

    fun delete(id: Long)

    fun existsEmail(email: String): Boolean

    fun userByEmail(email: String): Optional<UserModel>
}