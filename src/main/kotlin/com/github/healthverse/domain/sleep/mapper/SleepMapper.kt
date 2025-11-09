package com.github.healthverse.domain.sleep.mapper

import com.github.healthverse.domain.sleep.dto.SleepCreateDto
import com.github.healthverse.domain.sleep.dto.SleepDto
import com.github.healthverse.domain.sleep.entity.Sleep

object SleepMapper {

    fun toSleepDto(
        sleep: Sleep
    ) = SleepDto(
        id = sleep.id,
        userId = sleep.userId,
        wakeTime = sleep.wakeTime,
        sleepTime = sleep.sleepTime,
        sleepStore = sleep.sleepStore,
        memo = sleep.memo
    )

    fun toSleep(
        sleepCreateDto: SleepCreateDto,
    ) = Sleep.create(
        sleepTime = sleepCreateDto.sleepTime,
        wakeTime = sleepCreateDto.wakeTime,
        sleepStore = sleepCreateDto.sleepStore,
        memo = sleepCreateDto.memo,
        userId = sleepCreateDto.userId
    )
}