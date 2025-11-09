package com.github.healthverse.domain.sleep.entity

import com.github.healthverse.global.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.time.LocalDateTime


@Entity
class Sleep private constructor(
    sleepTime: LocalDateTime,
    wakeTime: LocalDateTime,
    sleepStore: Int,
    memo: String?,
    userId: Long
) : BaseEntity() {

    var sleepTime: LocalDateTime = sleepTime
        protected set

    var wakeTime: LocalDateTime = wakeTime
        protected set

    var sleepStore: Int = sleepStore
        protected set

    var memo: String? = memo
        protected set

    @Column(name = "user_id", nullable = false)
    var userId: Long = userId
        protected set

    companion object {
        fun create(
            sleepTime: LocalDateTime,
            wakeTime: LocalDateTime,
            sleepStore: Int,
            memo: String?,
            userId: Long
        ): Sleep {
            require(sleepStore in 1..10) { "Sleep store must be between 1 and 10" }
            return Sleep(sleepTime, wakeTime, sleepStore, memo, userId)
        }
    }
}
