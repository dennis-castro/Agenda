package api.agendamentopacientes.user.repository

import api.agendamentopacientes.user.entity.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional


@Repository
interface UserRepository : JpaRepository<UserModel, Long> {


    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String):Optional<UserModel>

}