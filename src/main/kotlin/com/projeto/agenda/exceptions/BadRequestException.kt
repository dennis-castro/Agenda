package com.projeto.agenda.exceptions


class BadRequestException(override val message: String, val errorCode: String) : Exception() {
}