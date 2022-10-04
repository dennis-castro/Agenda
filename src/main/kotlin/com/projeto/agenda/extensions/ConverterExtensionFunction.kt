package com.projeto.agenda.extensions

import com.projeto.agenda.controllers.models.request.PostPatientRequest
import com.projeto.agenda.controllers.models.request.PostScheduleRequest
import com.projeto.agenda.controllers.models.request.PutPatientRequest
import com.projeto.agenda.controllers.models.response.PatientResponse
import com.projeto.agenda.controllers.models.response.ScheduleResponse
import com.projeto.agenda.entities.Patient
import com.projeto.agenda.entities.Schedule
import com.projeto.agenda.enums.PatientStatus


//Patient

fun PostPatientRequest.toPatientEntity(): Patient {
    return Patient(
        name = this.name,
        email = this.email,
        status = PatientStatus.ACTIVE
    )
}

fun PutPatientRequest.toPatientEntity(patientSaved: Patient): Patient {
    return Patient(
        id = patientSaved.id,
        name = this.name ?: patientSaved.name,
        email = this.email ?: patientSaved.email,
        status = patientSaved.status
    )
}

fun Patient.toResponse(): PatientResponse {
    return PatientResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status,
    )
}

//Schedule

fun PostScheduleRequest.toScheduleEntity(patient: Patient): Schedule {
    return Schedule(
        dateTime = this.dateTime,
        description = this.description,
        patient = patient
    )
}

fun Schedule.toResponse(): ScheduleResponse {
    return ScheduleResponse(
        id = this.id,
        dateTime = this.dateTime,
        description = this.description,
        patient = this.patient,
    )
}



