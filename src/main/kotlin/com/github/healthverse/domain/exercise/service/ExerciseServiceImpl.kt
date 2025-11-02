package com.github.healthverse.domain.exercise.service

import com.github.healthverse.domain.exercise.dto.RecordExerciseDto
import com.github.healthverse.domain.exercise.dto.SavedExerciseDto
import com.github.healthverse.domain.exercise.mapper.ExerciseMapper.toExercise
import com.github.healthverse.domain.exercise.mapper.ExerciseMapper.toSavedExerciseDto
import com.github.healthverse.domain.exercise.repository.ExerciseRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class ExerciseServiceImpl(
    private val exerciseRepository: ExerciseRepository
): ExerciseService {
    override fun getExerciseHistories(
        userId: Long,
        startDate: LocalDate,
        endDate: LocalDate
    ): List<SavedExerciseDto> = exerciseRepository
        .findByUserIdAndCreatedAtBetweenOrderByCreatedAtDesc(
            userId = userId,
            startDateTime = startDate.atStartOfDay(),
            endDateTime = endDate.plusDays(1).atStartOfDay())
        .let { exercises -> exercises.map(::toSavedExerciseDto) }

    @Transactional
    override fun recordExercise(
        userId: Long,
        recordExerciseDto: RecordExerciseDto
    ) = toSavedExerciseDto(exerciseRepository.save(toExercise(userId, recordExerciseDto)))
}