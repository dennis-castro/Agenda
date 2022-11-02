package api.agendamentopacientes.user.controller

import api.agendamentopacientes.user.controller.request.PostUserRequest
import api.agendamentopacientes.user.controller.request.PutUserRequest
import api.agendamentopacientes.user.controller.response.UserResponse
import api.agendamentopacientes.user.extension.toUserModel
import api.agendamentopacientes.user.extension.toUserResponse
import api.agendamentopacientes.user.service.UserServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    lateinit var userService: UserServiceImpl


    @PostMapping
    fun create(@RequestBody @Valid userRequest: PostUserRequest) {
        userService.create(userRequest.toUserModel())
    }

    @GetMapping("/{id}")
    fun findUserById(@PathVariable id: Long): UserResponse {
        return userService.userById(id).toUserResponse()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid userRequest: PutUserRequest) {
        var userSaved = userService.userById(id)
        userService.update(userRequest.toUserModel(userSaved),id)
    }


    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        userService.delete(id)
    }


}


