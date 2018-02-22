package utils

fun <T> checkNotNull(value: T?, errorMessage: String): T = value ?: throw RuntimeException(errorMessage)

fun checkCondition(condition: () -> Boolean, exception: () -> RuntimeException) {
    if (condition()) exception()
}