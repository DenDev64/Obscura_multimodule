@file:JvmName("AuthFlowModelKt")

package com.obscura.llc.obscuraproject.presentation.activities.auth.flow

import com.obscura.llc.obscuraproject.presentation.base.BaseChildModel
import com.obscura.llc.obscuraproject.presentation.navigation.fragment_navigator.model.Pages
import com.obscura.llc.moduleproject.utils.validation.AuthFlowErrorModel

const val typeChildField: String="typeChild"
/**
 *
 */
const val errorField: String="error"
/**
 *
 */
const val emailField: String="email"
/**
 *
 */
const val passwordField: String="password"
/**
 *
 */
const val passwordConfirmField: String="passwordConfirm"
/**
 *
 */
const val agreeTermsField: String="agreeTerms"

class AuthFlowModel(type: Pages) : BaseChildModel() {

    /**
     * @field typeChild
     */
    var typeChild: Pages=type
        set(value) {
            field=value
            setChangedAndNotify(typeChildField)
        }

    /**
     * @field emailField
     */
    var email: String = ""
        set(value) {
            field=value
            setChangedAndNotify(emailField)
        }

    /**
     * @field password
     */
    var password: String=""
        set(value) {
            field=value
            setChangedAndNotify(passwordField)
        }

    /**
     * @field passwordConfirm
     */
    var passwordConfirm: String=""
        set(value) {
            field=value
            setChangedAndNotify(passwordConfirmField)
        }

    /**
     * @field agreeTerms
     */
    var agreeTerms: Boolean=false
        set(value) {
            field=value
            setChangedAndNotify(agreeTermsField)
        }

    /**
     * @field error
     */
    var error: AuthFlowErrorModel=AuthFlowErrorModel()
        set(value) {
            field=value
            setChangedAndNotify("error")
        }
}