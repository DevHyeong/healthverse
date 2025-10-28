package com.github.healthverse.global.utils

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.util.SerializationUtils
import java.util.*
import java.util.Base64

object CookieUtils {

    fun getCookie(request: HttpServletRequest, name: String): Cookie? {
        val cookies = request.cookies ?: return null
        return cookies.firstOrNull { it.name == name }
    }

    fun addCookie(response: HttpServletResponse, name: String, value: String, maxAge: Int) {
        val cookie = Cookie(name, value)
        cookie.path = "/"
        cookie.isHttpOnly = true
        cookie.maxAge = maxAge
        response.addCookie(cookie)
    }

    fun deleteCookie(request: HttpServletRequest, response: HttpServletResponse, name: String) {
        val cookie = Cookie(name, "")
        cookie.path = "/"
        cookie.maxAge = 0
        response.addCookie(cookie)
    }

    fun serialize(obj: Any): String {
        return Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(obj))
    }

    fun <T> deserialize(cookie: Cookie, cls: Class<T>): T {
        val bytes = Base64.getUrlDecoder().decode(cookie.value)
        return cls.cast(SerializationUtils.deserialize(bytes))
    }
}
