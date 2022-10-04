package com.projeto.agenda.enums

enum class Errors(val code: String, val message: String) {

    PA001("PA-001", "Invalid Request"),

    //patient
    PA101("PA-101", "Patient [%s] not exists"),
    PA102("PA-102", "Cannot update patient with status [%s]"),
    PA103("PA-103", "E-mail already registered"),

    //schedule
    PA201("PA-201", "Schedule [%s] not exists"),
    PA202("PA-202", "Already existing time")

}