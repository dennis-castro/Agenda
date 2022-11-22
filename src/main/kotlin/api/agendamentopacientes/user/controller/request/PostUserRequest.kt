package api.agendamentopacientes.user.controller.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty


data class PostUserRequest(


    @field:NotEmpty(message = "This field is required")
    var name: String,

    @field:NotEmpty(message = "This field is required")
    @Email
    var email: String,

    @field:NotEmpty(message = "password must be informed")
    var password: String

)
