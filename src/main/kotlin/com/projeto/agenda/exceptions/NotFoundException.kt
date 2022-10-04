package com.projeto.agenda.exceptions


class NotFoundException(override val message: String, val errorCode: String) : Exception() {
}