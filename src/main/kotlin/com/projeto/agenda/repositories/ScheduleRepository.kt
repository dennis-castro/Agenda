package com.projeto.agenda.repositories

import com.projeto.agenda.entities.Schedule
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
interface ScheduleRepository : JpaRepository<Schedule, Long> {

    fun findByDateTime(dateTime: LocalDateTime): Optional<Schedule>


}