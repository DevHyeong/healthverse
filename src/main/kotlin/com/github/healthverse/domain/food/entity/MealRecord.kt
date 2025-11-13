package com.github.healthverse.domain.food.entity

import com.github.healthverse.domain.exercise.entity.WeightTraining
import com.github.healthverse.global.entity.BaseEntity
import jakarta.persistence.*
import java.time.LocalDate

@Entity
class MealRecord(
    date: LocalDate,
    userId: Long
): BaseEntity() {

    var date: LocalDate = date
        protected set

    @Column(name = "user_id", nullable = false)
    var userId: Long = userId
        protected set

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    protected val mutableFoodIntakes: MutableList<FoodIntake> = mutableListOf()
    val foodIntakes: List<FoodIntake> get() = mutableFoodIntakes.toList()

    fun addFoodIntake(foodIntake: FoodIntake) {
        this.mutableFoodIntakes.add(foodIntake)
    }
}