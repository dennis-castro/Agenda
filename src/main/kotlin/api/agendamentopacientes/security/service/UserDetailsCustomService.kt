package api.agendamentopacientes.security.service

import api.agendamentopacientes.security.UserCustomDetails
import api.agendamentopacientes.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsCustomService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(id: String): UserDetails {
        val customer = userRepository.findById(id.toLong())
        return UserCustomDetails(customer.get())
    }
}