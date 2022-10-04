package com.projeto.agenda.controllers



import com.projeto.agenda.controllers.models.request.PostPatientRequest
import com.projeto.agenda.controllers.models.request.PutPatientRequest
import com.projeto.agenda.controllers.models.response.PatientResponse
import com.projeto.agenda.enums.Errors
import com.projeto.agenda.exceptions.BadRequestException
import com.projeto.agenda.extensions.toPatientEntity
import com.projeto.agenda.extensions.toResponse
import com.projeto.agenda.services.PatientServiceImpl
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("patient")
class PatientController(
    val patientService: PatientServiceImpl
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid patientRequest: PostPatientRequest) {
        patientService.create(patientRequest.toPatientEntity())
    }

    @GetMapping
    fun getAll(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<PatientResponse> {
        return patientService.findAll(pageable).map { it.toResponse() }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): PatientResponse {
        return patientService.findById(id).toResponse()
    }

    @GetMapping("/active")
    fun getActives(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<PatientResponse> {
        return patientService.findActives(pageable).map { it.toResponse() }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Long, @RequestBody @Valid patientRequest: PutPatientRequest) {
        var patientSaved = patientService.findById(id)

        if (patientRequest.email == null) {
            patientService.update(patientRequest.toPatientEntity(patientSaved))
        }

        if (patientRequest.email !== null) {
            val email = patientRequest.email

            if (!patientService.patientRepository.existsByEmail(email!!)) {
                patientService.update(patientRequest.toPatientEntity(patientSaved))
            }
            if (patientService.patientRepository.existsByEmail(email!!)) {
                val patientByEmail = patientService.patientRepository.findByEmail(email)
                if (patientByEmail.id == patientSaved.id) {
                    patientSaved.name = patientByEmail.name
                    patientSaved.email = patientByEmail.email
                    patientService.update(patientSaved)
                }
            }
            if (patientService.patientRepository.existsByEmail(email!!)) {
                val patientByEmail = patientService.patientRepository.findByEmail(email)
                if (patientByEmail.id !== patientSaved.id) {
                    throw BadRequestException(Errors.PA103.message, Errors.PA103.code)
                }
            }
            patientService.update(patientRequest.toPatientEntity(patientSaved))
        }
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        patientService.delete(id)
    }
}


