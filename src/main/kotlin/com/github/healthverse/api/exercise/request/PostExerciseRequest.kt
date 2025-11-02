package com.github.healthverse.api.exercise.request

import com.github.healthverse.domain.exercise.dto.RecordExerciseDto
import com.github.healthverse.domain.exercise.dto.WeightTrainingDto
import java.math.BigDecimal
import java.time.LocalDateTime

data class PostExerciseRequest(
    val startedAt: LocalDateTime,
    val endedAt: LocalDateTime?,
    val memo: String?,
    val weightTrainings: List<WeightTrainingVo>
){
    fun toRecordExerciseDto() = RecordExerciseDto(
        startedAt = this.startedAt,
        endedAt = this.endedAt,
        memo = this.memo,
        weightTrainings = this.weightTrainings.map {
            WeightTrainingDto(
                name = it.name,
                weight = it.weight,
                sets = it.sets,
                reps = it.reps,
                memo = it.memo
            )
        }
    )
}

data class WeightTrainingVo(
    val name: String,
    val weight: BigDecimal?,
    val sets: Int?,
    val reps: Int?,
    val memo: String?
)