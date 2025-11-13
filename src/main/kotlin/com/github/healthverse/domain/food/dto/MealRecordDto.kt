package com.github.healthverse.domain.food.dto

import java.time.LocalDate
import java.time.LocalDateTime

data class MealRecordDto(
    val id: Long,
    val date: LocalDate,
    val foodIntakes: List<FoodIntakeDto>
)

data class FoodIntakeDto(
    val id: Long,
    val intakeTime: LocalDateTime,
    val foods: String,
    val photoUrl: String?,
    val memo: String?
)