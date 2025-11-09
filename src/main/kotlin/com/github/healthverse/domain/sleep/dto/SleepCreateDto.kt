package com.github.healthverse.domain.sleep.dto

import java.time.LocalDateTime

data class SleepCreateDto(
    val userId: Long,
    val wakeTime: LocalDateTime,
    val sleepTime: LocalDateTime,
    val sleepStore: Int,
    val memo: String?,
)
