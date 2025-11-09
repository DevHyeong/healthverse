package com.github.healthverse.domain.sleep.service

import com.github.healthverse.domain.sleep.dto.SleepCreateDto
import com.github.healthverse.domain.sleep.dto.SleepDto
import java.time.LocalDate

interface SleepService {
    fun save(dto: SleepCreateDto): SleepDto
    fun getSleeps(userId: Long, startDate: LocalDate, endDate: LocalDate): List<SleepDto>
}