package com.github.healthverse.domain.exercise.repository

import com.github.healthverse.domain.exercise.entity.Exercise
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface ExerciseRepository: JpaRepository<Exercise, Long> {
    fun findByUserIdAndCreatedAtBetweenOrderByCreatedAtDesc(
        userId: Long,
        startDateTime: LocalDateTime,
        endDateTime: LocalDateTime
    ): List<Exercise>
}