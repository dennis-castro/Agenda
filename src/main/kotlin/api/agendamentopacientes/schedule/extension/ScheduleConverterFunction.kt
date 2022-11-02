package api.agendamentopacientes.schedule.extension

import api.agendamentopacientes.schedule.controller.request.PostScheduleRequest
import api.agendamentopacientes.schedule.controller.response.ScheduleResponse
import api.agendamentopacientes.schedule.entity.ScheduleModel
import api.agendamentopacientes.user.entity.UserModel

fun PostScheduleRequest.toScheduleModel(userModel: UserModel): ScheduleModel {
    return ScheduleModel(
        dateTime = this.dateTime,
        description = this.description,
        user = userModel
    )
}

fun ScheduleModel.toResponse():ScheduleResponse{
    return ScheduleResponse(
        id = this.id,
        dateTime = this.dateTime,
        description = this.description,
        user = this.user
    )
}