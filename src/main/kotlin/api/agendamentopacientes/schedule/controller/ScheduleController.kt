package api.agendamentopacientes.schedule.controller

import api.agendamentopacientes.schedule.controller.request.PostScheduleRequest
import api.agendamentopacientes.schedule.controller.response.ScheduleResponse
import api.agendamentopacientes.schedule.extension.toResponse
import api.agendamentopacientes.schedule.extension.toScheduleModel
import api.agendamentopacientes.schedule.service.ScheduleServiceImpl
import api.agendamentopacientes.user.service.UserServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/schedule")
class ScheduleController {

    @Autowired
    lateinit var scheduleService: ScheduleServiceImpl

    @Autowired
    lateinit var userService: UserServiceImpl


    @PostMapping
    fun create(@RequestBody @Valid scheduleRequest: PostScheduleRequest) {
        val userSaved = userService.userById(scheduleRequest.userId)
        scheduleService.create(scheduleRequest.toScheduleModel(userSaved))
    }

    @GetMapping
    fun getAll(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<ScheduleResponse> {
        return scheduleService.getAll(pageable).map { it.toResponse() }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ScheduleResponse {
        return scheduleService.scheduleById(id).toResponse()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        scheduleService.delete(id)
    }


}