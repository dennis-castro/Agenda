package com.projeto.agenda.services



import com.projeto.agenda.entities.Schedule
import com.projeto.agenda.enums.Errors
import com.projeto.agenda.enums.PatientStatus
import com.projeto.agenda.exceptions.BadRequestException
import com.projeto.agenda.exceptions.NotFoundException
import com.projeto.agenda.repositories.ScheduleRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ScheduleServiceImpl(
    val scheduleRepository: ScheduleRepository,
    val patientService: PatientServiceImpl
) : ScheduleService {


    override fun create(schedule: Schedule) {
        val patient = patientService.findById(schedule.patient.id!!)

        if (patient.status == PatientStatus.INACTIVE) {
            throw BadRequestException(Errors.PA102.message.format(patient.status), Errors.PA102.code)
        }

        var dateTime = scheduleRepository.findByDateTime(schedule.dateTime)

        if (dateTime.isPresent()) {
            throw NotFoundException(Errors.PA202.message, Errors.PA202.code)
        }

        schedule.registrationDate = LocalDateTime.now()
        schedule.patient = patient

        scheduleRepository.save(schedule)
    }


    override fun findActiveHours(pageable: Pageable): Page<Schedule> {
        return scheduleRepository.findAll(pageable)
    }

    override fun findActiveHoursById(id: Long): Schedule {
        return scheduleRepository.findById(id)
            .orElseThrow { NotFoundException(Errors.PA101.message.format(id), Errors.PA101.code) }
    }

    override fun delete(id: Long) {
        val schedule = findActiveHoursById(id)

        scheduleRepository.deleteById(id)
    }
}