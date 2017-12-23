package utils

fun <T> checkNotNull(value: T?, errorMessage: String): T = value ?: throw RuntimeException(errorMessage)