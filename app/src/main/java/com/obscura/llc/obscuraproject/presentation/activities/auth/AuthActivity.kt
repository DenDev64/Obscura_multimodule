package com.obscura.llc.obscuraproject.presentation.activities.auth

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.obscura.llc.obscuraproject.R
import com.obscura.llc.obscuraproject.databinding.AuthDataBinding
import com.obscura.llc.obscuraproject.di.component.ViewModelComponent
import com.obscura.llc.obscuraproject.domain.AuthViewModel
import com.obscura.llc.obscuraproject.presentation.activities.auth.flow.AuthFlowModel
import com.obscura.llc.obscuraproject.presentation.activities.auth.flow.AuthFlow
import com.obscura.llc.obscuraproject.presentation.base.BaseActivity
import com.obscura.llc.obscuraproject.presentation.navigation.fragment_navigator.model.PageNavigationItem
import com.obscura.llc.obscuraproject.presentation.navigation.fragment_navigator.model.Pages
import com.obscura.llc.obscuraproject.presentation.navigation.fragment_navigator.model.TransitionAnimation
import com.obscura.llc.obscuraproject.presentation.navigation.fragment_navigator.model.TransitionBundle
import com.obscura.llc.moduleproject.utils.AUTH_TYPE_SCREEN
import com.obscura.llc.obscuraproject.utils.extention.hideKeyboard

class AuthActivity : BaseActivity<AuthDataBinding>(), AuthFlow.AuthListener {

    private lateinit var viewModel: AuthViewModel
    /**
     * @param component
     */
    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProviders.of(this).get(AuthViewModel::class.java)
    }

    /**
     * @return
     */
    override fun getLayoutId(): Int=R.layout.activity_auth

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: AuthDataBinding) {
        handlePage()
        showRootChild()
    }

    /**
     * @param flowModel
     * @param callback
     */
    override fun authRequest(flowModel: AuthFlowModel, callback: AuthFlow.AuthCallback) {
        viewModel.authRequest(flowModel, callback)
    }

    /**
     * @param type
     * @param callback
     */
    override fun socialAuth(type: AuthFlow.SocialAuthType, callback: AuthFlow.AuthCallback?) {
        when (type) {
            AuthFlow.SocialAuthType.FACEBOOK -> {
                //test
                navigator.openMainScreen()
            }
            AuthFlow.SocialAuthType.GOOGLE -> {
                //test
                navigator.openMainScreen()
            }
        }
    }

    /**
     *
     */
    override fun openScreen(page: Pages) {
        goToPage(PageNavigationItem(page), TransitionBundle(TransitionAnimation.ENTER_FROM_RIGHT))
    }

    /**
     *
     */
    override fun routBack() {
        super.onBackPressed()
    }

    private fun showRootChild() {
        openScreen(handlePage())
        hideKeyboard()
    }

    private fun handlePage() = intent?.extras?.getSerializable(AUTH_TYPE_SCREEN) as Pages
}
