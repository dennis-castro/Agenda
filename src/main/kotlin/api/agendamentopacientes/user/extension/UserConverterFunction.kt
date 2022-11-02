package api.agendamentopacientes.user.extension

import api.agendamentopacientes.user.controller.request.PostUserRequest
import api.agendamentopacientes.user.controller.request.PutUserRequest
import api.agendamentopacientes.user.controller.response.UserResponse
import api.agendamentopacientes.user.entity.UserModel

fun PostUserRequest.toUserModel(): UserModel {
    return UserModel(
        name = this.name,
        email = this.email,
    )
}

fun PutUserRequest.toUserModel(userSaved: UserModel): UserModel {
    return UserModel(
        id = userSaved.id,
        name = this.name ?: userSaved.name,
        email = this.email ?: userSaved.email,
        status = userSaved.status
    )
}

fun UserModel.toUserResponse():UserResponse{
    return UserResponse(
        name = this.name,
        email = this.email,
        status = this.status
    )
}

