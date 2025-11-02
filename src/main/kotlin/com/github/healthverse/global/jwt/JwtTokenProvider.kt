package com.github.healthverse.global.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.github.healthverse.domain.user.entity.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret}") private val secret: String
) {
    fun generateAccessToken(user: User): String {
        val now = Date()
        val expiry = Date(now.time + 1000L * 60 * 60) // 1시간
        return JWT.create()
            .withSubject(user.id.toString())
            .withClaim("socialId", user.socialId)
            .withClaim("socialType", user.socialType.name)
            .withIssuedAt(now)
            .withExpiresAt(expiry)
            .sign(Algorithm.HMAC256(secret))
    }

    fun generateRefreshToken(user: User): String {
        val now = Date()
        val expiry = Date(now.time + 1000L * 60 * 60 * 24 * 7) // 7일
        return JWT.create()
            .withSubject(user.id.toString())
            .withClaim("socialId", user.socialId)
            .withClaim("socialType", user.socialType.name)
            .withIssuedAt(now)
            .withExpiresAt(expiry)
            .sign(Algorithm.HMAC256(secret))
    }

    fun validateAndGetUserId(token: String): Long {
        val decodedJWT = JWT.require(Algorithm.HMAC256(secret))
            .build()
            .verify(token)

        return decodedJWT.subject.toLong()
    }
}