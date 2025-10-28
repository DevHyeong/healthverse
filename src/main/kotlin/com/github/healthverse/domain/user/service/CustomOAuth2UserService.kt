package com.github.healthverse.domain.user.service

import com.github.healthverse.domain.user.entity.User
import com.github.healthverse.domain.user.provider.KakaoOAuth2ProviderImpl
import com.github.healthverse.domain.user.provider.NaverOAuth2ProviderImpl
import com.github.healthverse.domain.user.provider.OAuth2Provider
import com.github.healthverse.domain.user.repository.UserRepository
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class CustomOAuth2UserService(
    private val userRepository: UserRepository,
    private val oAuth2Providers: List<OAuth2Provider> = listOf(
        KakaoOAuth2ProviderImpl(),
        NaverOAuth2ProviderImpl()
    )
): DefaultOAuth2UserService() {
    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        val oAuth2User = super.loadUser(userRequest)
        val registrationId = userRequest?.clientRegistration?.registrationId
        val attributes = oAuth2User.attributes

        val oAuth2Provider = oAuth2Providers
            .find { provider -> provider.getSocialType().name.lowercase() == registrationId }
            ?: throw IllegalArgumentException("No OAuth2Provider found for registration ID: $registrationId")

        val socialId = oAuth2Provider.getSocialId(attributes)
        val user = User(socialId = socialId, socialType = oAuth2Provider.getSocialType())

        userRepository.findBySocialId(socialId)
            ?: userRepository.save(user)

        return DefaultOAuth2User(
            oAuth2User.authorities,
            attributes,
            "id"
        )
    }
}