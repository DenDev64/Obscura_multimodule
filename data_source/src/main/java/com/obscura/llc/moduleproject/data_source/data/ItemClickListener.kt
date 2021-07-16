package com.obscura.llc.moduleproject.data_source.data

interface ItemClickListener<T: BaseModel> {
    fun onClick(item: T)
}