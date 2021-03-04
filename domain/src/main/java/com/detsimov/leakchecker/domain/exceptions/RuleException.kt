package com.detsimov.leakchecker.domain.exceptions

open class RuleException(value: String, rule: String) :
    Exception("Rule exception with $value [$rule]")