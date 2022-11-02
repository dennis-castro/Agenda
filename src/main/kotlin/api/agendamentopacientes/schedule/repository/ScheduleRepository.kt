package api.agendamentopacientes.schedule.repository

import api.agendamentopacientes.schedule.entity.ScheduleModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*


@Repository
interface ScheduleRepository : JpaRepository<ScheduleModel, Long> {

    fun findByDateTime(dateTime: LocalDateTime): Optional<ScheduleModel>

}