package com.github.healthverse.domain.food.mapper

import com.github.healthverse.domain.food.dto.FoodIntakeDto
import com.github.healthverse.domain.food.dto.MealRecordCreateDto
import com.github.healthverse.domain.food.dto.MealRecordDto
import com.github.healthverse.domain.food.entity.FoodIntake
import com.github.healthverse.domain.food.entity.MealRecord

object FoodMapper {

    fun toMealRecord(dto: MealRecordCreateDto): MealRecord {
        val mealRecord = MealRecord(dto.date, dto.userId)
        dto.foodIntakes.forEach { foodIntakeCreateDto ->
            val foodIntake = FoodIntake(
                intakeTime = foodIntakeCreateDto.intakeTime,
                foods = foodIntakeCreateDto.foods,
                photoUrl = foodIntakeCreateDto.photoUrl,
                memo = foodIntakeCreateDto.memo
            )
            mealRecord.addFoodIntake(foodIntake)
        }
        return mealRecord
    }

    fun toMealRecordDto(mealRecord: MealRecord): MealRecordDto {
        val foodIntakeDtos = mealRecord.foodIntakes.map { foodIntake ->
            FoodIntakeDto(
                id = foodIntake.id!!,
                intakeTime = foodIntake.intakeTime,
                foods = foodIntake.foods,
                photoUrl = foodIntake.photoUrl,
                memo = foodIntake.memo
            )
        }
        return MealRecordDto(
            id = mealRecord.id,
            date = mealRecord.date,
            foodIntakes = foodIntakeDtos
        )
    }

}