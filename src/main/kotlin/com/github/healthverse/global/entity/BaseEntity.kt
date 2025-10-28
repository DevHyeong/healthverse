package com.github.healthverse.global.entity

import com.github.healthverse.domain.user.entity.User
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime


@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    var createdAt : LocalDateTime? = null
        protected set

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    var createdBy: User? = null
        protected set

    var updatedAt : LocalDateTime? = null
        protected set

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    var updatedBy: User? = null
        protected set
}