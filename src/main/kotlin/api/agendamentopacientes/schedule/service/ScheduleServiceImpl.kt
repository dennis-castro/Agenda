package api.agendamentopacientes.schedule.service

import api.agendamentopacientes.exception.BadRequestException
import api.agendamentopacientes.exception.NotFoundException
import api.agendamentopacientes.exception.enum.Errors
import api.agendamentopacientes.schedule.entity.ScheduleModel
import api.agendamentopacientes.schedule.repository.ScheduleRepository
import api.agendamentopacientes.user.enum.UserStatus
import api.agendamentopacientes.user.service.UserServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class ScheduleServiceImpl:ScheduleService {

    @Autowired
    lateinit var scheduleRepository: ScheduleRepository

    @Autowired
    lateinit var userService: UserServiceImpl


    override fun create(scheduleModel: ScheduleModel) {
        val userSaved = userService.getById(scheduleModel.user.id!!)

        if (userSaved.status == UserStatus.INACTIVE) {
            throw BadRequestException(Errors.PA102.message.format(userSaved.status), Errors.PA102.code)
        }

        val dateTime = scheduleRepository.findByDateTime(scheduleModel.dateTime)

        if (dateTime.isPresent) {
            throw NotFoundException(Errors.PA202.message, Errors.PA202.code)
        }

        scheduleModel.registrationDate = LocalDateTime.now()

        if (scheduleModel.dateTime <= scheduleModel.registrationDate) {
            throw BadRequestException(Errors.PA203.message, Errors.PA203.code)
        }

        scheduleModel.user = userSaved

        scheduleRepository.save(scheduleModel)
    }


    override fun getById(id: Long): ScheduleModel {
        return scheduleRepository.findById(id)
            .orElseThrow { NotFoundException(Errors.PA201.message.format(id), Errors.PA201.code) }
    }


    override fun getAll(pageable: Pageable): Page<ScheduleModel> {
        return scheduleRepository.findAll(pageable)
    }


    override fun delete(id: Long) {
        getById(id)
        scheduleRepository.deleteById(id)
    }
}