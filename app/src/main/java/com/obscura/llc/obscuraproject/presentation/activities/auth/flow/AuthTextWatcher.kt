package com.obscura.llc.obscuraproject.presentation.activities.auth.flow

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.obscura.llc.obscuraproject.R

open class AuthTextWatcher(
    private val callback: AuthFlow.AuthCallback,
    private val authChildCases: AuthFlowModel,
    private val editText : EditText
) : TextWatcher {

    /**
     *
     */
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    /**
     *
     */
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

    /**
     *
     */
    override fun afterTextChanged(s: Editable) {
        getField(editText)
        callback.hideError()
    }

    private fun getField(editText: EditText) {
        when (editText.id) {
            R.id.etAuthEmail -> {
                authChildCases.email=editText.text.toString().trim()
            }
            R.id.etAuthPassword -> {
                authChildCases.password=editText.text.toString().trim()
            }
            R.id.etAuthConfirmPassword -> {
                authChildCases.passwordConfirm=editText.text.toString().trim()
            }
        }
    }
}