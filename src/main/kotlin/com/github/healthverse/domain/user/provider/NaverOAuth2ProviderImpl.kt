package com.github.healthverse.domain.user.provider

import com.github.healthverse.domain.user.enums.SocialType


class NaverOAuth2ProviderImpl: OAuth2Provider {
    override fun getSocialId(attributes: Map<String, Any>): String {
        val response = attributes["response"] as? Map<*, *>
        val email = response?.get("email") as? String
        return email ?: throw IllegalArgumentException("Email not found in Naver response")
    }

    override fun getSocialType(): SocialType = SocialType.NAVER
}