package api.agendamentopacientes.user.controller.response

import api.agendamentopacientes.user.enum.UserStatus

data class UserResponse(

    var name :String,

    var email:String,

    var status: UserStatus
)
