package com.github.healthverse.domain.sleep.service

import com.github.healthverse.domain.sleep.dto.SleepCreateDto
import com.github.healthverse.domain.sleep.dto.SleepDto
import com.github.healthverse.domain.sleep.mapper.SleepMapper.toSleep
import com.github.healthverse.domain.sleep.mapper.SleepMapper.toSleepDto
import com.github.healthverse.domain.sleep.repository.SleepRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class SleepServiceImpl(
    private val sleepRepository: SleepRepository
): SleepService {
    override fun save(
        dto: SleepCreateDto
    ): SleepDto {
        // TODO validation check (e.g., overlapping sleep times)

        return toSleepDto(sleepRepository.save(toSleep(dto)))
    }

    override fun getSleeps(
        userId: Long,
        startDate: LocalDate,
        endDate: LocalDate
    ): List<SleepDto> {
        return sleepRepository.findByUserIdAndCreatedAtBetweenOrderByCreatedAtDesc(
            userId = userId,
            startDateTime = startDate.atStartOfDay(),
            endDateTime = endDate.plusDays(1).atStartOfDay()
        ).map(::toSleepDto)
    }
}