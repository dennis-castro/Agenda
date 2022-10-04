package com.projeto.agenda.controllers




import com.projeto.agenda.controllers.models.request.PostScheduleRequest
import com.projeto.agenda.controllers.models.response.ScheduleResponse
import com.projeto.agenda.extensions.toResponse
import com.projeto.agenda.extensions.toScheduleEntity
import com.projeto.agenda.services.PatientServiceImpl
import com.projeto.agenda.services.ScheduleServiceImpl
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("schedule")
class ScheduleController(
    var scheduleService: ScheduleServiceImpl,
    var patientService: PatientServiceImpl
) {


    @PostMapping
    fun create(@RequestBody @Valid scheduleRequest: PostScheduleRequest) {
        val patient = patientService.findById(scheduleRequest.patientId)
        scheduleService.create(scheduleRequest.toScheduleEntity(patient))
    }

    @GetMapping
    fun getAll(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<ScheduleResponse> {
        return scheduleService.findActiveHours(pageable).map { it.toResponse() }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ScheduleResponse {
        return scheduleService.findActiveHoursById(id).toResponse()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        scheduleService.delete(id)
    }
}