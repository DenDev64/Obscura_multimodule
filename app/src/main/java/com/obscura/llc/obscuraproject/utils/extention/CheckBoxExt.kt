package com.obscura.llc.obscuraproject.utils.extention

import android.widget.CheckBox
import com.obscura.llc.obscuraproject.R

fun CheckBox.addErrorForCheckBox() {
    this.apply {
        this.postDelayed({
            this.buttonDrawable=
                this.context.getDrawable(R.drawable.ic_check_box_error)
        }, 200)
    }
}

fun CheckBox.clearErrorForCheckBox() {
    this.apply {
        this.buttonDrawable=this.context.getDrawable(R.drawable.toggle_checkbox)
    }
}

