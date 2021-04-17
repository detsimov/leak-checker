package com.detsimov.leakchecker.ui_android.firebase

enum class EVENT {

    /** Евенты банера на главном экране */
    AD_LOADED_BANNER,
    AD_CLICK_BANNER,
    AD_CLOSE_BANNER,
    AD_ERROR_BANNER,

    /** Евенты взаимодействия юзера с чем-то */
    USER_ADD_TRACK_DATA_CLICK,
    USER_ADD_TRACK_DATA_SUCCESS,
    USER_ADD_TRACK_DATA_CATCH_OVERFLOW,
    USER_CLICK_ON_ANALYSE_SCAN,
    USER_NOT_ACCEPT_CONSENT,

    /** Consent ошибки */
    CONSENT_INFO_UPDATE_ERROR,
    CONSENT_LOAD_FORM_ERROR,
    CONSENT_SHOW_FORM_ERROR
}