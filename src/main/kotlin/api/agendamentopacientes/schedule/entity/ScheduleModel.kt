package api.agendamentopacientes.schedule.entity

import api.agendamentopacientes.user.entity.UserModel
import java.time.LocalDateTime
import javax.persistence.*


@Entity(name = "schedule_db")
data class ScheduleModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column
    var registrationDate: LocalDateTime? = null,

    @Column
    var dateTime: LocalDateTime,

    @Column
    var description: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: UserModel
)
