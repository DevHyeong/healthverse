package com.github.healthverse.domain.exercise.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class RecordExerciseDto(
    val startedAt: LocalDateTime,
    val endedAt: LocalDateTime?,
    val memo: String?,
    val weightTrainings: List<WeightTrainingDto>
)

data class SavedExerciseDto(
    val exerciseId: Long,
    val startedAt: LocalDateTime,
    val endedAt: LocalDateTime?,
    val memo: String?,
    val weightTrainings: List<WeightTrainingDto>,
    val createdAt: LocalDateTime,
)

data class WeightTrainingDto(
    val name: String,
    val weight: BigDecimal?,
    val sets: Int?,
    val reps: Int?,
    val memo: String?
)
