package com.obscura.llc.obscuraproject.presentation.base

import android.content.Context
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.obscura.llc.obscuraproject.presentation.activities.auth.flow.AuthFlowModel
import com.obscura.llc.obscuraproject.presentation.activities.auth.flow.AuthFlowModelBinding
import com.obscura.llc.obscuraproject.presentation.activities.auth.flow.AuthTextWatcher
import com.obscura.llc.obscuraproject.presentation.activities.auth.flow.AuthFlow

abstract class BaseAuthFragment<V : ViewDataBinding>: BaseFragment<V>(), AuthFlow.AuthCallback {

    protected var bindingModel : AuthFlowModelBinding? = null
    protected var listener: AuthFlow.AuthListener? = null
    protected lateinit var authChildCases : AuthFlowModel
    protected lateinit var textWatcher: AuthTextWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupTypeScreen()
        prepareBindingModel()
    }

    /**
     * @param context
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AuthFlow.AuthListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    /**
     *
     */
    override fun onDetach() {
        super.onDetach()
        bindingModel = null
        listener = null
        authChildCases.deleteObservers()
    }

    abstract fun prepareBindingModel()

    abstract fun setupTypeScreen()

}