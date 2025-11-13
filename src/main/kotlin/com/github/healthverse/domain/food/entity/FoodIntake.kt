package com.github.healthverse.domain.food.entity

import com.github.healthverse.global.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
class FoodIntake(
    intakeTime: LocalDateTime,
    photoUrl: String?,
    foods: String,
    memo: String?
): BaseEntity() {

    @ManyToOne
    var mealRecord: MealRecord? = null
        protected set

    @Column(nullable = false)
    var intakeTime: LocalDateTime = intakeTime
        protected set

    @Column(nullable = false)
    var foods: String = foods
        protected set

    var photoUrl: String? = photoUrl
        protected set

    var memo: String? = memo
        protected set
}