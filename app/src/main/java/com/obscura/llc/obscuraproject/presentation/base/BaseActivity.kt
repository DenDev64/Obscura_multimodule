package com.obscura.llc.obscuraproject.presentation.base

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.obscura.llc.obscuraproject.R
import com.obscura.llc.obscuraproject.di.component.ViewModelComponent
import com.obscura.llc.obscuraproject.presentation.navigation.Navigation
import com.obscura.llc.obscuraproject.presentation.navigation.NavigationImpl
import com.obscura.llc.obscuraproject.presentation.navigation.fragment_navigator.model.PageNavigationItem
import com.obscura.llc.obscuraproject.presentation.navigation.fragment_navigator.model.Pages
import com.obscura.llc.obscuraproject.presentation.navigation.fragment_navigator.model.TransitionBundle
import com.obscura.llc.moduleproject.utils.NetworkConnectionListener
import com.obscura.llc.moduleproject.utils.NetworkConnectionProvider
import com.obscura.llc.moduleproject.utils.NetworkConnectionProviderImpl
import com.obscura.llc.obscuraproject.utils.extention.hideKeyboard
import com.obscura.llc.obscuraproject.utils.extention.initializeToolbar
import com.obscura.llc.moduleproject.utils.permission.IPermissionManager
import com.obscura.llc.moduleproject.utils.permission.PermissionManagerImpl

abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity(), NetworkConnectionProvider {

    /**
     *
     */
    protected lateinit var viewBinding: V

    /**
     *
     */
    protected lateinit var navigator: Navigation

    /**
     *
     */
    protected lateinit var permissionManager: IPermissionManager

    /**
     *
     */
    open lateinit var messageNecessaryPermissions: String

    private var toolbar: Toolbar?=null

    private val networkConnectionProvider = NetworkConnectionProviderImpl(this)

    /**
     *
     */
    override fun listenConnectionChanges(listener: NetworkConnectionListener) {
        networkConnectionProvider.listenConnectionChanges(listener)
    }

    /**
     *
     */
    override fun ignoreConnectionChanges(listener: NetworkConnectionListener) {
        networkConnectionProvider.ignoreConnectionChanges(listener)
    }

    companion object {
        private const val DEBUG_ENABLED=false
        var currentActivity: Class<*>? = null
    }

    /**
     * @param component
     */
    abstract fun injectDependency(component: ViewModelComponent)

    /**
     * @return
     */
    abstract fun getLayoutId(): Int

    /**
     * @param binder
     */
    abstract fun setupViewLogic(binder: V)

    override fun onStart() {
        super.onStart()
        currentActivity = this::class.java
    }

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=DataBindingUtil.setContentView(this, getLayoutId())
        navigator=NavigationImpl(this)
        permissionManager=PermissionManagerImpl()
        createDaggerDependencies()
        setupViewLogic(viewBinding)
    }

    override fun onDestroy() {
        networkConnectionProvider.destroy()
        if (currentActivity == javaClass) {
            currentActivity = null
        }
        super.onDestroy()
    }

    /**
     *
     */
    @RequiresApi(Build.VERSION_CODES.ECLAIR)
    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
    }

    /**
     *
     */
    @RequiresApi(Build.VERSION_CODES.ECLAIR)
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down)
    }

    /**
     * handle back stack with handling state of children of activity
     */
    override fun onBackPressed() {
        hideKeyboard()
        if (!navigator.back()) {
            super.onBackPressed()
        }
    }

    /**
     * Convenience method for adding a fragment or replacing an existing one for a specific tag
     * @param fragment Fragment
     * @param id Int
     * @param tag String
     */
    protected fun addOrReplaceFragment(fragment: Fragment, id: Int, tag: String) {
        val fragmentTransaction=supportFragmentManager.beginTransaction()
        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            fragmentTransaction.add(id, fragment, tag)
        } else {
            fragmentTransaction.replace(id, fragment, tag)
        }
        fragmentTransaction.commit()
    }

    protected fun setWindowFlag(bits: Int, on: Boolean) {
        val win=window
        val winParams=win.attributes
        if (on) {
            winParams.flags=winParams.flags or bits
        } else {
            winParams.flags=winParams.flags and bits.inv()
        }
        win.attributes=winParams
    }

    fun goToConversation(talkerId: Int){
        navigator.openConversationScreen(talkerId)
    }

    fun goToDetailListActivity(page: Pages){
        navigator.openListScreen(page)
    }

    fun goToDetailProfileActivity(userId: Int) {
        navigator.openProfileScreen(userId)
    }

    fun goToDetailFeedElementActivity(themeId: Int) {
        navigator.openFeedElementScreen(themeId)
    }

    fun goToPage(page: PageNavigationItem) {
        navigator.goToPage(page)
    }

    fun goToPage(page: PageNavigationItem, transitionBundle: TransitionBundle) {
        navigator.goToPage(page, transitionBundle)
    }

    fun goToPageForResult(page: PageNavigationItem, transitionBundle: TransitionBundle) {
        navigator.goToPageForResult(page, transitionBundle)
    }

    fun back(): Boolean {
        if (!navigator.back()) {
            onBackPressed()
        }
        return true
    }

    protected fun reset() {
        navigator.reset()
    }

    /**
     *
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected fun initializeToolbar(toolbar: Toolbar) {
        this.toolbar=toolbar.initializeToolbar(actionBar, this)
    }

    /**
     *
     */
    protected fun getToolbar(): Toolbar?=this.toolbar

    /**
     * check permissions and handel them state
     */
    @TargetApi(Build.VERSION_CODES.M)
    protected fun isPermissionGranted(arrayPermission: Array<String>, requestCode: Int=0): Boolean=
        if (this.permissionManager.hasPermission(this, arrayPermission)) {
            true
        } else {
            this.permissionManager.requestPermission(this, requestCode, arrayPermission)
            false
        }

    /**
     * for navigate to android settings
     */
    protected fun showNoGalleryPermission() {
        navigator.openSettings("package:$packageName")
        Toast.makeText(this, messageNecessaryPermissions, Toast.LENGTH_LONG).show()
    }

    /**
     *
     */
    open fun isDebugEnabled(): Boolean=DEBUG_ENABLED

    private fun createDaggerDependencies() {
        injectDependency((application as com.obscura.llc.obscuraproject.App).getViewModelComponent())
    }
}
