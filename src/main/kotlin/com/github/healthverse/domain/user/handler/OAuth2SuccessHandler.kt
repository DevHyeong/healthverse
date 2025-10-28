package com.github.healthverse.domain.user.handler

import com.github.healthverse.domain.user.repository.UserRepository
import com.github.healthverse.global.jwt.JwtTokenProvider
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder

@Component
class OAuth2SuccessHandler(
    @Value("\${front.url}") private val frontUrl: String,
    @Value("\${front.callback-path}") private val frontCallbackPath: String,
    private val jwtTokenProvider: JwtTokenProvider,
    private val userRepository: UserRepository
) : AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val oAuth2User = authentication.principal as OAuth2User

        val socialId = oAuth2User.attributes["id"] as Long
        val user = userRepository.findBySocialId(socialId.toString())
            ?: throw IllegalStateException("사용자를 찾을 수 없습니다")

        val accessToken = jwtTokenProvider.generateAccessToken(user)
        val refreshToken = jwtTokenProvider.generateRefreshToken(user)

        val redirectUrl = UriComponentsBuilder
            .fromUriString("$frontUrl/$frontCallbackPath")
            .queryParam("accessToken", accessToken)
            .queryParam("refreshToken", refreshToken)
            .build()
            .toUriString()

        response.sendRedirect(redirectUrl)
    }
}