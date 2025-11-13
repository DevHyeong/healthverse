package com.github.healthverse.domain.food.dto

import java.time.LocalDate
import java.time.LocalDateTime

data class MealRecordCreateDto(
    val userId: Long,
    val date: LocalDate,
    val foodIntakes: List<FoodIntakeCreateDto>
)

data class FoodIntakeCreateDto(
    val intakeTime: LocalDateTime,
    val foods: String,
    val photoUrl: String?,
    val memo: String?
)
