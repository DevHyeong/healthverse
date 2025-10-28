package com.github.healthverse.domain.user.entity

import com.github.healthverse.domain.user.enums.Sex
import com.github.healthverse.domain.user.enums.SocialType
import com.github.healthverse.global.entity.BaseEntity
import jakarta.persistence.Entity
import java.time.LocalDate

@Entity
class User(
    socialId: String,
    socialType: SocialType,
    birthDate: LocalDate? = null,
    sex: Sex? = null,
    weight: Double? = null,
): BaseEntity() {

    var socialId: String = socialId
        protected set

    var socialType: SocialType = socialType
        protected set

    var birthDate: LocalDate? = birthDate
        protected set

    var sex: Sex? = sex
        protected set

    var weight: Double? = weight
        protected set
}