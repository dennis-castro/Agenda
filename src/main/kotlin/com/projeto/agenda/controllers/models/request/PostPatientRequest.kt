package com.projeto.agenda.controllers.models.request


import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty


data class PostPatientRequest(

    @NotEmpty(message = "This field is required")
    var name: String,

    @NotEmpty(message = "This field is required")
    @Email
    var email: String,

    )
