package com.github.healthverse.api.sleep.controller

import com.github.healthverse.api.sleep.request.PostSleepRequest
import com.github.healthverse.domain.sleep.service.SleepService
import com.github.healthverse.global.vo.ApiSuccessResponse
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api/v1/sleeps")
class SleepController(
    private val sleepService: SleepService
) {

    @GetMapping
    fun getSleeps(
        @AuthenticationPrincipal userId: Long,
        @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("startDate") startDate: LocalDate,
        @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("endDate") endDate: LocalDate,
    ) = sleepService.getSleeps(
        userId = userId,
        startDate = startDate,
        endDate = endDate
    ).let { ApiSuccessResponse(data = it) }

    @PostMapping
    fun postSleep(
        @AuthenticationPrincipal userId: Long,
        @RequestBody request: PostSleepRequest
    ) = sleepService.save(request.toSleepCreateDto(userId))
             .let { ApiSuccessResponse(data = it) }
}