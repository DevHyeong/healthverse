package com.github.healthverse.domain.exercise.service

import com.github.healthverse.domain.exercise.dto.RecordExerciseDto
import com.github.healthverse.domain.exercise.dto.SavedExerciseDto
import java.time.LocalDate

interface ExerciseService {
    fun getExerciseHistories(userId: Long, startDate: LocalDate, endDate: LocalDate): List<SavedExerciseDto>
    fun recordExercise(userId: Long, recordExerciseDto: RecordExerciseDto): SavedExerciseDto
}