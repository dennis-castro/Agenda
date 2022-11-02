package api.agendamentopacientes.schedule.controller.response

import api.agendamentopacientes.user.entity.UserModel
import java.time.LocalDateTime

data class ScheduleResponse(

    var id: Long? = null,

    var dateTime: LocalDateTime,

    var description: String,

    var user: UserModel
)
