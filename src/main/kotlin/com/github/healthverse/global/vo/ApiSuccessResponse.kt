package com.github.healthverse.global.vo

import org.springframework.http.HttpStatus

data class ApiSuccessResponse<T>(
    val status: HttpStatus = HttpStatus.OK,
    val data: T
)
