package api.agendamentopacientes.schedule.service

import api.agendamentopacientes.schedule.entity.ScheduleModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ScheduleService {

    fun create(scheduleModel: ScheduleModel)

    fun scheduleById(id: Long):ScheduleModel

    fun getAll(pageable: Pageable): Page<ScheduleModel>

    fun delete(id: Long)

}