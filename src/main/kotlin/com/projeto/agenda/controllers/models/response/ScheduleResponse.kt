package com.projeto.agenda.controllers.models.response


import com.projeto.agenda.entities.Patient
import java.time.LocalDateTime

data class ScheduleResponse(

    var id: Long? = null,

    var dateTime: LocalDateTime,

    var description: String,

    var patient: Patient

)
