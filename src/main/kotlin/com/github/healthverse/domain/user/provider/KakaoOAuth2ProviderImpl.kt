package com.github.healthverse.domain.user.provider

import com.github.healthverse.domain.user.enums.SocialType

class KakaoOAuth2ProviderImpl: OAuth2Provider {
    override fun getSocialId(attributes: Map<String, Any>): String {
        var id = attributes["id"] as Long
        return id.toString()
    }

    override fun getSocialType(): SocialType = SocialType.KAKAO
}