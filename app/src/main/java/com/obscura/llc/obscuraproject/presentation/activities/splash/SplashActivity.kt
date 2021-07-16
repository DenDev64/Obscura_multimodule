package com.obscura.llc.obscuraproject.presentation.activities.splash

import android.os.Handler
import com.obscura.llc.obscuraproject.R
import com.obscura.llc.obscuraproject.databinding.SplashBinding
import com.obscura.llc.obscuraproject.di.component.ViewModelComponent
import com.obscura.llc.obscuraproject.presentation.base.BaseActivity
import com.obscura.llc.moduleproject.utils.DELAY_3000

class SplashActivity : BaseActivity<SplashBinding>() {

    /**
     * @param
     */
    override fun injectDependency(component: ViewModelComponent) {
       component.inject(this)
    }

    /**
     * @return
     */
    override fun getLayoutId(): Int = R.layout.activity_splash

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: SplashBinding) {
        (application as com.obscura.llc.obscuraproject.App).saveMockToDatabase()
        Handler().postDelayed({
            navigator.openWelcomeScreen(this)
            finish()
        }, DELAY_3000)
    }
}
