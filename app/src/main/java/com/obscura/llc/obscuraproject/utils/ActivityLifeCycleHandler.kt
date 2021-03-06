package com.obscura.llc.obscuraproject.utils

import android.annotation.TargetApi
import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.obscura.llc.obscuraproject.utils.UIUtil.getInsecureDeviceDialog
import com.obscura.llc.moduleproject.utils.security.RootDetectionUtil
import com.obscura.llc.moduleproject.utils.security.SkipSecureCheckActivity

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
class ActivityLifeCycleHandler : ActivityLifecycleCallbacks {

    override fun onActivityCreated(
        activity: Activity,
        savedInstanceState: Bundle
    ) {
        ++created
        if (com.obscura.llc.obscuraproject.App.isLeakCanaryEnabled()) {
            if (activity is FragmentActivity) {
                activity.supportFragmentManager
                    .registerFragmentLifecycleCallbacks(
                        object : FragmentManager.FragmentLifecycleCallbacks() {
                            override fun onFragmentDestroyed(
                                fm: FragmentManager,
                                fragment: Fragment
                            ) {
                                super.onFragmentDestroyed(fm, fragment)
                                com.obscura.llc.obscuraproject.App.getRefWatcher()
                                    .watch(fragment)
                            }
                        }, true
                    )
            }
        }
    }

    override fun onActivityDestroyed(activity: Activity) {
        ++destroyed
        if (com.obscura.llc.obscuraproject.App.isLeakCanaryEnabled()) {
            com.obscura.llc.obscuraproject.App.getRefWatcher().watch(activity)
        }
    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {
        ++resumed
        if (activity !is SkipSecureCheckActivity) {
            RootDetectionUtil.isSecureDevice(activity, getInsecureDeviceDialog())
        }
    }

    override fun onActivitySaveInstanceState(
        activity: Activity,
        outState: Bundle
    ) {
    }

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {
        ++stopped
    }

    companion object {

        private var resumed: Int = 0
        private var destroyed: Int = 0
        private var created: Int = 0
        private var stopped: Int = 0
        private val paused: Int = 0

        // check if in foreground
        val isAppInForeground: Boolean
            get() = resumed > stopped

        val numberOfRunningActivities: Int
            get() = created - destroyed
    }
}
