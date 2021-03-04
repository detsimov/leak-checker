package com.detsimov.leakchecker.domain.repositories

import com.detsimov.leakchecker.domain.exceptions.RuleException


interface IRuleRepository {

    @Throws(PhoneRuleException::class)
    fun checkRuPhoneNumber(number: String)

    @Throws(EmailRuleException::class)
    fun checkEmail(email: String)

}

class PhoneRuleException(value: String) : RuleException(value, "Phone")

class EmailRuleException(value: String) : RuleException(value, "Email")