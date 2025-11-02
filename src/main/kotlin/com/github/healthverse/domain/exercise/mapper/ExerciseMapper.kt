package com.github.healthverse.domain.exercise.mapper

import com.github.healthverse.domain.exercise.dto.RecordExerciseDto
import com.github.healthverse.domain.exercise.dto.SavedExerciseDto
import com.github.healthverse.domain.exercise.dto.WeightTrainingDto
import com.github.healthverse.domain.exercise.entity.Exercise
import com.github.healthverse.domain.exercise.entity.WeightTraining
import java.time.LocalDateTime

object ExerciseMapper {

    fun toExercise(
        userId: Long,
        dto: RecordExerciseDto
    ): Exercise {
        val exercise = Exercise(
            startedAt = dto.startedAt,
            endedAt = dto.endedAt,
            memo = dto.memo,
            userId = userId
        )

        dto.weightTrainings.map { toWeightTraining(it, exercise) }
            .forEach { exercise.addWeightTraining(it) }

        return exercise
    }

    fun toWeightTraining(
        dto: WeightTrainingDto,
        exercise: Exercise
    ): WeightTraining = WeightTraining(
        exercise = exercise,
        name = dto.name,
        weight = dto.weight,
        sets = dto.sets,
        reps = dto.reps,
        memo = dto.memo ,
    )

    fun toSavedExerciseDto(
        exercise: Exercise
    ): SavedExerciseDto = SavedExerciseDto(
        exerciseId = exercise.id,
        startedAt = exercise.startedAt,
        endedAt = exercise.endedAt,
        memo = exercise.memo,
        weightTrainings = exercise.weightTrainings.map(::toWeightTrainingDto),
        createdAt = exercise.createdAt ?: LocalDateTime.now()

    )

    fun toWeightTrainingDto(
        weightTraining: WeightTraining
    ): WeightTrainingDto = WeightTrainingDto(
        name = weightTraining.name,
        weight = weightTraining.weight,
        sets = weightTraining.sets,
        reps = weightTraining.reps,
        memo = weightTraining.memo
    )
}