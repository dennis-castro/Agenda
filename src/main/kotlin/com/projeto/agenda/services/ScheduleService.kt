package com.projeto.agenda.services


import com.projeto.agenda.entities.Schedule
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ScheduleService {

    fun create(schedule: Schedule)

    fun findActiveHours(pageable: Pageable): Page<Schedule>

    fun findActiveHoursById(id: Long): Schedule

    fun delete(id: Long)


}