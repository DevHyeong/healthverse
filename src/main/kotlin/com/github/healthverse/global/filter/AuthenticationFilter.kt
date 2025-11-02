package com.github.healthverse.global.filter

import com.github.healthverse.domain.user.repository.UserRepository
import com.github.healthverse.global.jwt.JwtTokenProvider
import com.github.healthverse.global.utils.LoggerUtils.logger
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import kotlin.math.log

@Component
class AuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider,
    private val userRepository: UserRepository
): Filter {

    val log = logger()

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse

        val authKey = httpRequest.getHeader("Authorization")
        if (authKey.isNullOrEmpty()) {
            httpResponse.status = HttpServletResponse.SC_UNAUTHORIZED
            httpResponse.writer.write("Missing Authorization Key")
            return
        }

        try {
            // JWT 검증 및 사용자 ID 추출
            val userId = jwtTokenProvider.validateAndGetUserId(authKey)

            // 사용자 정보 조회
            val user = userRepository.findById(userId)
                .orElseThrow { IllegalStateException("User not found") }

            // SecurityContext에 인증 정보 설정
            val authentication = UsernamePasswordAuthenticationToken(user.id, null, emptyList())
            SecurityContextHolder.getContext().authentication = authentication

            // 다음 필터로 요청 전달
            chain.doFilter(request, response)
        } catch (e: Exception) {
            e.printStackTrace()
            httpResponse.status = HttpServletResponse.SC_UNAUTHORIZED
            httpResponse.writer.write("Invalid Authorization Key")
        }
    }
}