package com.github.healthverse.domain.sleep.repository

import com.github.healthverse.domain.sleep.entity.Sleep
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface SleepRepository: JpaRepository<Sleep, Long> {
    fun findByUserIdAndCreatedAtBetweenOrderByCreatedAtDesc(
        userId: Long,
        startDateTime: LocalDateTime,
        endDateTime: LocalDateTime
    ): List<Sleep>
}