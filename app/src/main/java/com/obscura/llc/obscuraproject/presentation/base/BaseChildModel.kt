package com.obscura.llc.obscuraproject.presentation.base

import java.util.*

open class BaseChildModel : Observable() {

    protected fun setChangedAndNotify(field: Any) {
        setChanged()
        notifyObservers(field)
    }
}