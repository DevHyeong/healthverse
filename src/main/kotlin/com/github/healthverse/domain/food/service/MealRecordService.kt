package com.github.healthverse.domain.food.service

import com.github.healthverse.domain.food.dto.MealRecordCreateDto
import com.github.healthverse.domain.food.dto.MealRecordDto
import java.time.LocalDate

interface MealRecordService {
    fun save(dto: MealRecordCreateDto): MealRecordDto
    fun getMealRecords(userId: Long, startDate: LocalDate, endDate: LocalDate): List<MealRecordDto>
}