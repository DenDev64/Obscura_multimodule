package com.obscura.llc.obscuraproject.presentation.activities.auth.flow

import android.text.TextWatcher
import com.obscura.llc.obscuraproject.presentation.base.BaseFlow
import com.obscura.llc.moduleproject.utils.validation.AuthFlowErrorModel

interface AuthFlow : BaseFlow {

    /**
     * for social auth
     */
    enum class SocialAuthType {

        /**
         *
         */
        GOOGLE,

        /**
         *
         */
        FACEBOOK
    }

    /**
     *
     */
    interface AuthListener : BaseFlow.BaseListener {
        fun routBack()
        fun authRequest(flowModel: AuthFlowModel, callback: AuthCallback)
        fun socialAuth(type: SocialAuthType, callback: AuthCallback?)
    }

    /**
     *
     */
    interface AuthCallback : BaseFlow.IBaseCallback, TextWatcher {
        fun showError(error: AuthFlowErrorModel)
        fun hideError()
    }
}