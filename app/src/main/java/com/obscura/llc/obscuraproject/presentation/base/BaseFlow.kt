package com.obscura.llc.obscuraproject.presentation.base

import com.obscura.llc.obscuraproject.presentation.navigation.fragment_navigator.model.Pages

interface BaseFlow {
     interface BaseListener {
         fun openScreen(page: Pages)
     }

    interface IBaseCallback
}
