package api.agendamentopacientes.exception.enum

enum class Errors(val code: String, val message: String) {

    PA001("PA-001", "Invalid Request"),

    //user
    PA101("PA-101", "User [%s] not exists"),
    PA102("PA-102", "Cannot update user with status [%s]"),
    PA103("PA-103", "E-mail already registered"),

    //schedule
    PA201("PA-201", "Schedule [%s] not exists"),
    PA202("PA-202", "Already existing time"),
    PA203("PA-203", "this time is invalid")

}