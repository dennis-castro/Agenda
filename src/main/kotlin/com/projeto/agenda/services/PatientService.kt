package com.projeto.agenda.services

import com.projeto.agenda.entities.Patient
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PatientService {


    fun create(patient: Patient)

    fun findById(id: Long): Patient

    fun findActives(pageable: Pageable): Page<Patient>

    fun update(patient: Patient)

    fun delete(id: Long)

    fun findAll(pageable: Pageable): Page<Patient>


}