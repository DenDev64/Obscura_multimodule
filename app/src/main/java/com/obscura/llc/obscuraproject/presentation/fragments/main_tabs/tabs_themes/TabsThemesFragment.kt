package com.obscura.llc.obscuraproject.presentation.fragments.main_tabs.tabs_themes

import android.os.Bundle
import com.obscura.llc.obscuraproject.databinding.TabsThemesDataBinding
import com.obscura.llc.obscuraproject.R
import com.obscura.llc.obscuraproject.presentation.base.BaseFragment
import com.obscura.llc.obscuraproject.presentation.widget.pageview.model.TabPages

class TabsThemesFragment : BaseFragment<TabsThemesDataBinding>() {
    val bindingModel =
        TabsThemesBindingModel(
            initThemesTabs()
        )
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
     * @return
     */
    override fun getLayoutId(): Int = R.layout.fragment_tabs_themes

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: TabsThemesDataBinding) {
        binding.bindingModel = bindingModel
    }

    private fun initThemesTabs() : List<TabPages> {
        val tabs: MutableList<TabPages> = mutableListOf()
        tabs.add(TabPages.TAB_THEMES)
        tabs.add(TabPages.TAB_EVENTS)
        tabs.add(TabPages.TAB_ARTICLES)
        return tabs
    }

    companion object {
        @JvmStatic
        fun newInstance(): TabsThemesFragment {
            return TabsThemesFragment()
        }
    }
}