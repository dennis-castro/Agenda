package com.projeto.agenda.repositories


import com.projeto.agenda.entities.Patient
import com.projeto.agenda.enums.PatientStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface PatientRepository : JpaRepository<Patient, Long> {


    fun findByStatus(active: PatientStatus, pageable: Pageable): Page<Patient>
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Patient


}