package com.github.healthverse.global.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object LoggerUtils {
    inline fun <reified T> T.logger(): Logger = LoggerFactory.getLogger(T::class.java)
}