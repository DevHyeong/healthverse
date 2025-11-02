package com.github.healthverse.api.exercise.controller

import com.github.healthverse.api.exercise.request.PostExerciseRequest
import com.github.healthverse.domain.exercise.service.ExerciseService
import com.github.healthverse.global.vo.ApiSuccessResponse
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/api/v1/exercises")
class ExerciseController(
    private val exerciseService: ExerciseService
) {

    @GetMapping("/health-check")
    fun healthCheck(
        @AuthenticationPrincipal principal: Long,
    ) = ApiSuccessResponse(data = "Exercise Service is up and running! ${principal}")

    @GetMapping
    fun getExercises(
        @AuthenticationPrincipal userId: Long,
        @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("startDate") startDate: LocalDate,
        @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("endDate") endDate: LocalDate,
    ) = exerciseService.getExerciseHistories(userId, startDate, endDate)
            .let { ApiSuccessResponse(data = it) }

    @PostMapping
    fun postExercise(
        @AuthenticationPrincipal userId: Long,
        @RequestBody request: PostExerciseRequest
    ) = exerciseService.recordExercise(userId, request.toRecordExerciseDto())
        .let { ApiSuccessResponse(data = it) }
}