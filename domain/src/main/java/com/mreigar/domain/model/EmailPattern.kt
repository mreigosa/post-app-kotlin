package com.mreigar.domain.model

enum class EmailPattern {
    NO_VALID_PATTERN, INFO, UK;

    fun getPattern(): String = when (this) {
        INFO -> ".info"
        UK -> ".co.uk"
        else -> ""
    }
}

fun getEmailPatternFromEmail(email: String): EmailPattern {
    return when {
        email.endsWith(EmailPattern.INFO.getPattern()) -> EmailPattern.INFO
        email.endsWith(EmailPattern.UK.getPattern()) -> EmailPattern.UK
        else -> EmailPattern.NO_VALID_PATTERN
    }
}