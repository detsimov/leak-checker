package com.detsimov.leakchecker.data_local.repositories

import com.detsimov.leakchecker.domain.repositories.EmailRuleException
import com.detsimov.leakchecker.domain.repositories.IRuleRepository
import com.detsimov.leakchecker.domain.repositories.PhoneRuleException
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots

internal class RuleRepository : IRuleRepository {

    private val mask = MaskImpl(PredefinedSlots.RUS_PHONE_NUMBER, true)

    override fun checkRuPhoneNumber(number: String) {
        val isPhoneValid = mask.run {
            clear()
            insertFront(number)
            filled()
        }
        if (isPhoneValid.not()) throw PhoneRuleException(number)
    }

    override fun checkEmail(email: String) {
        if (email.isEmpty()) throw EmailRuleException(email)
    }
}