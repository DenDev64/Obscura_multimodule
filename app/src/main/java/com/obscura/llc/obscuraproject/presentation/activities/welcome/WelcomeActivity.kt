package com.obscura.llc.obscuraproject.presentation.activities.welcome

import com.obscura.llc.obscuraproject.R
import com.obscura.llc.obscuraproject.databinding.WelcomeDataBinding
import com.obscura.llc.obscuraproject.di.component.ViewModelComponent
import com.obscura.llc.obscuraproject.presentation.activities.welcome.flow.WelcomeFlowModelBinding
import com.obscura.llc.obscuraproject.presentation.base.BaseActivity

class WelcomeActivity :  BaseActivity<WelcomeDataBinding>() {

    /**
     * @param component
     */
    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    /**
     * @return
     */
    override fun getLayoutId(): Int = R.layout.activity_welcome

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: WelcomeDataBinding) {
        binding.navigator = navigator
        binding.bindingModel = WelcomeFlowModelBinding(this)
    }
}
