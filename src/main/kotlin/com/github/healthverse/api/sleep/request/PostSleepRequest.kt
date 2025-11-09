package com.github.healthverse.api.sleep.request

import com.github.healthverse.domain.sleep.dto.SleepCreateDto
import java.time.LocalDateTime

data class PostSleepRequest(
    val sleepTime: LocalDateTime,
    val wakeTime: LocalDateTime,
    val sleepStore: Int,
    val memo: String?
) {
    fun toSleepCreateDto(userId: Long): SleepCreateDto {
        return SleepCreateDto(
            sleepTime = this.sleepTime,
            wakeTime = this.wakeTime,
            sleepStore = this.sleepStore,
            memo = this.memo,
            userId = userId
        )
    }
}
