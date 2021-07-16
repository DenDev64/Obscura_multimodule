package com.obscura.llc.moduleproject.remote_data_source.pojo

import com.google.gson.annotations.SerializedName
import com.obscura.llc.moduleproject.data_source.database.entity.UserEntity

class UserResponse {
    @SerializedName("records")
    var records: List<UserEntity>? = null
}