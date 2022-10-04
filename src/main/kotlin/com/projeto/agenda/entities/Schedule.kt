package com.projeto.agenda.entities

import java.time.LocalDateTime
import javax.persistence.*


@Entity(name = "schedule")
data class Schedule(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column
    var registrationDate: LocalDateTime? = null,

    @Column
    var dateTime: LocalDateTime,

    @Column
    var description: String,


    @ManyToOne
    @JoinColumn(name = "patient_id")
    var patient: Patient
)
