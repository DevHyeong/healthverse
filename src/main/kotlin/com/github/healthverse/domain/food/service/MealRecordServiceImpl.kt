package com.github.healthverse.domain.food.service

import com.github.healthverse.domain.food.dto.MealRecordCreateDto
import com.github.healthverse.domain.food.dto.MealRecordDto
import com.github.healthverse.domain.food.mapper.FoodMapper.toMealRecord
import com.github.healthverse.domain.food.mapper.FoodMapper.toMealRecordDto
import com.github.healthverse.domain.food.repository.MealRecordRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class MealRecordServiceImpl(
    private val mealRecordRepository: MealRecordRepository
): MealRecordService {

    override fun save(dto: MealRecordCreateDto): MealRecordDto {
        return toMealRecordDto(mealRecordRepository.save(toMealRecord(dto)))
    }

    override fun getMealRecords(userId: Long, startDate: LocalDate, endDate: LocalDate): List<MealRecordDto> {
        return mealRecordRepository
            .findByUserIdAndDateBetweenOrderByDateDesc(
                userId = userId,
                startDate = startDate,
                endDate = endDate
            )
            .map(::toMealRecordDto)
    }


}