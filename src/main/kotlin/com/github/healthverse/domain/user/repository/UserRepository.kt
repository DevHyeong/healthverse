package com.github.healthverse.domain.user.repository

import com.github.healthverse.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findBySocialId(socialId: String): User?
}