package com.projeto.agenda.services



import com.projeto.agenda.entities.Patient
import com.projeto.agenda.enums.Errors
import com.projeto.agenda.enums.PatientStatus
import com.projeto.agenda.exceptions.BadRequestException
import com.projeto.agenda.exceptions.NotFoundException
import com.projeto.agenda.repositories.PatientRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PatientServiceImpl(
    val patientRepository: PatientRepository
) : PatientService {


    override fun create(patient: Patient) {
        var emailSaved = patientRepository.existsByEmail(patient.email)
        if (emailSaved) {
            throw BadRequestException(Errors.PA103.message, Errors.PA103.code)
        }
        patientRepository.save(patient)
    }

    override fun findAll(pageable: Pageable): Page<Patient> {
        return patientRepository.findAll(pageable)
    }


    override fun findById(id: Long): Patient {
        return patientRepository.findById(id)
            .orElseThrow { NotFoundException(Errors.PA101.message.format(id), Errors.PA101.code) }
    }


    override fun findActives(pageable: Pageable): Page<Patient> {
        return patientRepository.findByStatus(PatientStatus.ACTIVE, pageable)
    }

    override fun update(patient: Patient) {
        if (patient.status == PatientStatus.INACTIVE) {
            throw BadRequestException(Errors.PA102.message.format(patient.status), Errors.PA102.code)
        }
        patientRepository.save(patient)
    }

    override fun delete(id: Long) {
        val patient = findById(id)
        if (patient.status == PatientStatus.INACTIVE) {
            throw BadRequestException(Errors.PA102.message.format(patient.status), Errors.PA102.code)
        }
        patient.status = PatientStatus.INACTIVE
        patientRepository.save(patient)
    }


}