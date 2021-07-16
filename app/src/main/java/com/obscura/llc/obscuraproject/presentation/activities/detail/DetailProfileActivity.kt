package com.obscura.llc.obscuraproject.presentation.activities.detail

import android.view.MenuItem
import com.obscura.llc.obscuraproject.R
import com.obscura.llc.obscuraproject.databinding.DetailProfileDataBinding
import com.obscura.llc.obscuraproject.di.component.ViewModelComponent
import com.obscura.llc.obscuraproject.presentation.base.BaseActivity

class DetailProfileActivity: BaseActivity<DetailProfileDataBinding>() {
    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_detail_profile

    override fun setupViewLogic(binder: DetailProfileDataBinding) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Profile"
    }

    /**
     *
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }
}