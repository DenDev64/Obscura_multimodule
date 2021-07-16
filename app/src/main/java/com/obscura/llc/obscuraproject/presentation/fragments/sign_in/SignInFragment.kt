package com.obscura.llc.obscuraproject.presentation.fragments.sign_in

import android.os.Bundle
import android.text.Editable
import com.obscura.llc.obscuraproject.R
import com.obscura.llc.obscuraproject.databinding.SignInDataBinding
import com.obscura.llc.obscuraproject.presentation.activities.auth.flow.*
import com.obscura.llc.obscuraproject.presentation.base.BaseAuthFragment
import com.obscura.llc.obscuraproject.presentation.navigation.fragment_navigator.model.Pages
import com.obscura.llc.moduleproject.utils.validation.AuthFlowErrorModel

class SignInFragment : BaseAuthFragment<SignInDataBinding>() {

    /**
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //ToDo get extras from bundle
        }
    }

    /**
     *
     */
    override fun setupTypeScreen(){
        authChildCases =  AuthFlowModel(Pages.SIGN_IN)
    }

    /**
     *
     */
    override fun prepareBindingModel(){
        bindingModel = AuthFlowModelBinding(authChildCases, listener,this)
    }

    /**
     * @return
     */
    override fun getLayoutId(): Int = R.layout.fragment_sign_in

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: SignInDataBinding) {
        bindingModel?.apply {
            binding.bindingModel=this
        }
        binding.etAuthEmail.addTextChangedListener(
            AuthTextWatcher(this,
                authChildCases,
                binding.etAuthEmail
            )
        )
        binding.etAuthPassword.addTextChangedListener(
            AuthTextWatcher(this,
                authChildCases,
                binding.etAuthPassword
            )
        )
    }

    /**
     * @param error
     */
    override fun showError(error: AuthFlowErrorModel) {
        authChildCases.apply {
            this.error = error
        }
    }

    override fun hideError() {
        authChildCases.error= AuthFlowErrorModel()
    }

    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
       authChildCases.error= AuthFlowErrorModel()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SignInFragment().apply {
                arguments = Bundle().apply {
                    //ToDo put bundle
                }
            }
    }
}
