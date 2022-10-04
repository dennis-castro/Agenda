package com.projeto.agenda.entities

import com.projeto.agenda.enums.PatientStatus
import javax.persistence.*


@Entity(name = "patient")
data class Patient(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "name")
    var name: String,

    @Column(name = "email")
    var email: String,


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: PatientStatus,
)
