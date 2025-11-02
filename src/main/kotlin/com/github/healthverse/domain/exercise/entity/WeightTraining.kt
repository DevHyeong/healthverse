package com.github.healthverse.domain.exercise.entity

import com.github.healthverse.global.entity.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import java.math.BigDecimal

@Entity
class WeightTraining(
    exercise: Exercise,
    name: String,
    weight: BigDecimal? = null,
    sets: Int? = null,
    reps: Int? = null,
    memo: String? = null,
): BaseEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    var exercise: Exercise = exercise
        protected set

    var name: String = name
        protected set

    var weight: BigDecimal? = weight
        protected set

    var sets: Int? = sets
        protected set

    var reps: Int? = reps
        protected set

    var memo: String? = memo
        protected set
}