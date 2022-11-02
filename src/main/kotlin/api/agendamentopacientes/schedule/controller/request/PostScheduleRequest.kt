package api.agendamentopacientes.schedule.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.validation.constraints.Future
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

data class PostScheduleRequest(

    @NotEmpty(message = "This field is required")
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    var dateTime: LocalDateTime,

    @NotBlank(message = "This field is required")
    var description: String,

    @NotEmpty(message = "This field is required")
    @JsonAlias("user_id")
    var userId: Long

)
