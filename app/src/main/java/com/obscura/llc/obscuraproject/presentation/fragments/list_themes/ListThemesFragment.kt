package com.obscura.llc.obscuraproject.presentation.fragments.list_themes

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.obscura.llc.obscuraproject.BR
import com.obscura.llc.obscuraproject.R
import com.obscura.llc.obscuraproject.databinding.ListThemesDataBinding
import com.obscura.llc.obscuraproject.di.component.ViewModelComponent
import com.obscura.llc.obscuraproject.domain.FeedViewModel
import com.obscura.llc.obscuraproject.presentation.base.BasePagingFragment
import com.obscura.llc.obscuraproject.presentation.navigation.fragment_navigator.model.Pages
import com.obscura.llc.obscuraproject.presentation.widget.pageview.model.TabPages
import com.obscura.llc.moduleproject.utils.EXTRAS_PAGES
import com.obscura.llc.moduleproject.utils.FIRST_LIST_POSITION
import com.obscura.llc.obscuraproject.utils.FeedLayoutManager
import com.obscura.llc.moduleproject.utils.MIN_LIST_SIZE
import com.obscura.llc.obscuraproject.utils.extention.showSnack
import com.obscura.llc.obscuraproject.utils.multistate.MultiStateView
import com.obscura.llc.moduleproject.data_source.data.BaseCardModel
import com.obscura.llc.moduleproject.data_source.data.ItemClickListener
import com.obscura.llc.moduleproject.data_source.database.entity.ThemeEntity
import javax.inject.Inject

class ListThemesFragment : BasePagingFragment<ListThemesDataBinding>(),
    ItemClickListener<ThemeEntity> {

    /**
     *
     */
    var viewModel: FeedViewModel?=null
        @Inject set

    /**
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {bundle ->
            //todo check and refactor
            bundle.getSerializable(EXTRAS_PAGES)?.toString()?.let {
                screenType=it
            }
        }
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    /**
     * @return
     */
    override fun getLayoutId(): Int=R.layout.fragment_list_themes

    /**
     * @return
     */
    override fun getListView(): RecyclerView=viewBinder.multiStateView.listView

    /**
     * @return
     */
    override fun getRefreshView(): SwipeRefreshLayout=viewBinder.swipeRefresh

    /**
     *
     */
    override fun getStateView(): MultiStateView=viewBinder.multiStateView.multiState

    /**
     *
     */
    override fun initListView() {
        context?.let {
            val layoutManager=
                FeedLayoutManager(it)

            getListView().layoutManager=
                layoutManager

            getListView().adapter=
                pagingAdapter


            pagingAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {

                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    super.onItemRangeInserted(positionStart, itemCount)
                    if (positionStart == FIRST_LIST_POSITION && itemCount == MIN_LIST_SIZE) {
                        getListView().scrollToPosition(FIRST_LIST_POSITION)
                    }
                }
            })

            viewModel?.getRefreshing().let { viewBinder.setVariable(BR.refreshing, it) }

            viewModel?.isLoading().let { viewBinder.setVariable(BR.loading, it) }
        }
    }

    /**
     *
     */
    override fun initObserver(screenType : String) {
        viewModel?.initLiveData(screenType, this, this)
        viewModel?.getPagedList()?.observe(this, Observer(this@ListThemesFragment::onItemsLoaded))
    }

    /**
     *
     */
    override fun removeObserver() {
        viewModel?.getPagedList()?.removeObservers(this)
    }

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: ListThemesDataBinding) {
        viewModel?.fetchData(screenType)
        binding.swipeRefresh.setOnRefreshListener {
            viewModel?.setRefreshing(true)
            viewModel?.fetchData(screenType)
        }
    }

    /**
     *
     */
    override fun onItemsLoaded(items: PagedList<BaseCardModel>?) {
        if (items.isNullOrEmpty()) {
            getStateView().viewState=MultiStateView.VIEW_STATE_EMPTY
        } else {
            pagingAdapter.submitList(items)
            getStateView().viewState=MultiStateView.VIEW_STATE_CONTENT
        }
    }

    /**
     *
     */
    override fun displayProgress() {
        getStateView().viewState=MultiStateView.VIEW_STATE_LOADING
    }

    /**
     *
     */
    override fun onLoadError(errorMessage: String) {
        getStateView().viewState=MultiStateView.VIEW_STATE_ERROR
        activity?.showSnack(errorMessage)
    }

    override fun onDetach() {
        viewModel?.clearCachedItems(screenType)
        super.onDetach()
    }

    companion object {
        /**
         *
         */
        @JvmStatic
        fun newInstance(): ListThemesFragment {
            return ListThemesFragment()
        }

        /**
         *
         */
        @JvmStatic
        fun newInstance(pages: TabPages) =
            ListThemesFragment().apply {
                arguments = Bundle().apply {
                    this.putSerializable(EXTRAS_PAGES, pages)
                }
            }

        /**
         *
         */
        @JvmStatic
        fun newInstance(pages: Pages) =
            ListThemesFragment().apply {
                arguments = Bundle().apply {
                    this.putSerializable(EXTRAS_PAGES, pages)
                }
            }
    }

    override fun onClick(item: ThemeEntity) {
        getBaseActivity().goToDetailFeedElementActivity(item.id)
    }
}
