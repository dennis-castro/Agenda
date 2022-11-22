package api.agendamentopacientes.security

import api.agendamentopacientes.user.entity.UserModel
import api.agendamentopacientes.user.enum.UserStatus
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserCustomDetails(val userModel: UserModel) : UserDetails {

    val id: Long = userModel.id!!
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        userModel.roles.map { SimpleGrantedAuthority(it.description) }.toMutableList()

    override fun getPassword(): String = userModel.password
    override fun getUsername(): String = userModel.id.toString()
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = userModel.status == UserStatus.ACTIVE

}