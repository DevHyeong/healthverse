package com.github.healthverse.domain.exercise.entity

import com.github.healthverse.global.entity.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Exercise(
    startedAt: LocalDateTime,
    endedAt: LocalDateTime? = null,
    memo: String? = null,
    userId: Long
): BaseEntity() {

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    protected val mutableWeightTrainings: MutableList<WeightTraining> = mutableListOf()
    val weightTrainings: List<WeightTraining> get() = mutableWeightTrainings.toList()
        
    var startedAt: LocalDateTime = startedAt
        protected set

    var endedAt: LocalDateTime? = null
        protected set

    var memo: String? = null
        protected set

    @Column(name = "user_id", nullable = false)
    var userId: Long = userId
        protected set

    fun addWeightTraining(weightTraining: WeightTraining) {
        this.mutableWeightTrainings.add(weightTraining)
    }
}