package com.obscura.llc.obscuraproject.presentation.activities.detail

import android.content.Intent
import android.view.MenuItem
import com.obscura.llc.obscuraproject.R
import com.obscura.llc.obscuraproject.databinding.DetailListDataBinding
import com.obscura.llc.obscuraproject.di.component.ViewModelComponent
import com.obscura.llc.obscuraproject.presentation.base.BaseActivity
import com.obscura.llc.obscuraproject.presentation.fragments.list_themes.ListThemesFragment
import com.obscura.llc.obscuraproject.presentation.navigation.fragment_navigator.model.Pages
import com.obscura.llc.moduleproject.utils.EXTRAS_PAGES

/**
 *
 */
class DetailListActivity : BaseActivity<DetailListDataBinding>() {
    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_detail_list

    override fun setupViewLogic(binder: DetailListDataBinding) {
        val page = intent?.extras?.getSerializable(EXTRAS_PAGES) as Pages
        setupToolbar(page)
        supportFragmentManager.beginTransaction()
            .add(R.id.listFragmentContainer, ListThemesFragment.newInstance(page))
            .commit()
    }

    private fun setupToolbar(page: Pages)= supportActionBar?.apply {
        setDisplayHomeAsUpEnabled(true)
        when(page){
            Pages.CREATED_THEMES -> title= getString(R.string.created_themes_title)
            Pages.FOLLOWED_THEMES -> title= getString(R.string.followed_themes_title)
        }
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

    companion object{
        /**
         *
         */
        fun getIntent(navigatorSource: BaseActivity<*>, page: Pages): Intent= Intent(navigatorSource, DetailListActivity::class.java).apply {
            putExtra(EXTRAS_PAGES, page)
        }
    }
}