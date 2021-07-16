package com.obscura.llc.obscuraproject.presentation.fragments.profile.flow

import com.obscura.llc.obscuraproject.presentation.base.BaseFlow

interface ProfileFlow : BaseFlow {

    interface ProfileListener : BaseFlow.BaseListener {
        fun onLogout()
    }
}