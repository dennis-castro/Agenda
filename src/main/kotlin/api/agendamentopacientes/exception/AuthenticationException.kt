package api.agendamentopacientes.exception

class AuthenticationException(override val message: String, val errorCode: String) : Exception()