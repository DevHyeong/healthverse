package com.github.healthverse.domain.user.provider

import com.github.healthverse.domain.user.enums.SocialType

interface OAuth2Provider {
    fun getSocialId(attributes: Map<String, Any>): String
    fun getSocialType(): SocialType
}