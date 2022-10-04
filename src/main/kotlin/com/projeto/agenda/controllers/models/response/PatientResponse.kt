package com.projeto.agenda.controllers.models.response

import com.projeto.agenda.enums.PatientStatus


data class PatientResponse(


    var id: Long? = null,

    var name: String,

    var email: String,

    var status: PatientStatus,

    )
