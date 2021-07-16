package com.obscura.llc.obscuraproject.presentation.activities.detail

import android.view.MenuItem
import com.obscura.llc.obscuraproject.R
import com.obscura.llc.obscuraproject.databinding.DetailFeedDataBinding
import com.obscura.llc.obscuraproject.di.component.ViewModelComponent
import com.obscura.llc.obscuraproject.presentation.base.BaseActivity

class DetailFeedActivity: BaseActivity<DetailFeedDataBinding>() {
    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_detail_feed

    override fun setupViewLogic(binder: DetailFeedDataBinding) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Feed element"
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