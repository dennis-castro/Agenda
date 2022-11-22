package api.agendamentopacientes.user.entity

import api.agendamentopacientes.user.enum.Role
import api.agendamentopacientes.user.enum.UserStatus
import javax.persistence.*


@Entity(name = "user_db")
data class UserModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "name")
    var name: String,

    @Column(name = "email", unique = true)
    var email: String,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: UserStatus = UserStatus.ACTIVE,

    @Column(name="password")
    var password: String,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")])
    var roles: Set<Role> = setOf()




//    @OneToMany
//    @JoinColumn(name = "schedule_id")
//    var schedule: Schedule = setOf<Schedule>()

)
