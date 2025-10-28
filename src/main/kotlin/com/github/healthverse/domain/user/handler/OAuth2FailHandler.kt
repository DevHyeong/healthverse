package com.github.healthverse.domain.user.handler

import com.github.healthverse.global.utils.LoggerUtils.logger
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class OAuth2FailHandler(

): AuthenticationFailureHandler {

    val log = logger()

    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        exception.printStackTrace()
        log.error("OAuth2 로그인 실패: exception")
        println("OAuth2 로그인 실패: ${exception.message}")
        TODO("Not yet implemented")
    }
}