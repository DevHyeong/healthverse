package com.github.healthverse.domain.food.repository

import com.github.healthverse.domain.food.entity.MealRecord
import org.springframework.data.jpa.repository.JpaRepository

interface MealRecordRepository: JpaRepository<MealRecord, Long> {
    fun findByUserIdAndDateBetweenOrderByDateDesc(
        userId: Long,
        startDate: java.time.LocalDate,
        endDate: java.time.LocalDate
    ): List<MealRecord>
}