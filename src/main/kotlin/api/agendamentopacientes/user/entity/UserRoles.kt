package api.agendamentopacientes.user.entity

import javax.persistence.JoinColumn
import javax.persistence.Table

@Table(name = "user_roles")
data class UserRoles(

    @JoinColumn(name = "user_id")
    var userId: Long,

    var roler:String



)