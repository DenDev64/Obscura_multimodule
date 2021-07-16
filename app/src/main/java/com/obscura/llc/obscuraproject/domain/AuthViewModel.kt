package com.obscura.llc.obscuraproject.domain

import androidx.lifecycle.ViewModel
import com.obscura.llc.moduleproject.utils.validation.AuthFlowErrorModel
import com.obscura.llc.obscuraproject.presentation.activities.auth.flow.AuthFlowModel
import com.obscura.llc.obscuraproject.presentation.activities.auth.flow.AuthFlow
import com.obscura.llc.moduleproject.utils.validation.ValidationHandler
import com.obscura.llc.moduleproject.utils.validation.ValidationHandlerImpl

/**
 *
 */
class AuthViewModel : ViewModel() {

    private val validationHandler: ValidationHandler=ValidationHandlerImpl()

    /**
     *
     */
    fun authRequest(flowModel: AuthFlowModel, callback: AuthFlow.AuthCallback) {
        val validation: AuthFlowErrorModel=doValidation(flowModel)
        if (validation.emptyErrors()) {

        } else {
            callback.showError(validation)
        }
    }

    /**
     *
     */
    override fun onCleared() {
        super.onCleared()
    }

    private fun doValidation(flowModel: AuthFlowModel): AuthFlowErrorModel=
        validationHandler.validationAuthCases(
            flowModel.email,
            flowModel.password,
            flowModel.passwordConfirm,
            flowModel.agreeTerms
        )
}