package com.obscura.llc.obscuraproject.presentation.activities.main

import android.app.Activity
import android.content.Intent
import com.obscura.llc.obscuraproject.R
import com.obscura.llc.obscuraproject.databinding.MainDataBinding
import com.obscura.llc.obscuraproject.di.component.ViewModelComponent
import com.obscura.llc.obscuraproject.domain.AllUsersViewModel
import com.obscura.llc.obscuraproject.presentation.base.BaseActivity
import com.obscura.llc.obscuraproject.presentation.navigation.BottomNavigationHandler
import com.obscura.llc.moduleproject.utils.BUNDLE_LOCATION_LAT
import com.obscura.llc.moduleproject.utils.BUNDLE_LOCATION_LNG
import com.obscura.llc.moduleproject.utils.REQUEST_CODE_LOCATION
import com.obscura.llc.obscuraproject.utils.extention.showSnack
import javax.inject.Inject

class MainActivity : BaseActivity<MainDataBinding>() {

    var viewModel: AllUsersViewModel? = null @Inject set

    /**
     * @param component
     */
    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }
    /**
     * @return
     */
    override fun getLayoutId(): Int = R.layout.activity_main

    /**
     * @param binder
     */
    override fun setupViewLogic(binder: MainDataBinding) {
        BottomNavigationHandler(
            binder.bottomNavigation,
            binder.mainFragmentContainer,
            supportActionBar,
            supportFragmentManager
        )
        binder.fabMap.setOnClickListener {
            navigator.openLocationChooser()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_LOCATION -> {
                if (resultCode == Activity.RESULT_OK) {
                    showSnack(getString(R.string.location_chosen_text) + " " + data?.extras?.getDouble(
                        BUNDLE_LOCATION_LAT
                    ) + ", " + data?.extras?.getDouble(BUNDLE_LOCATION_LNG))
                } else {
                    showSnack(R.string.location_chosen_err)
                }
            }
        }
    }
}
