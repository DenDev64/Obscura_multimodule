package com.obscura.llc.moduleproject.data_source.data.card_models

import com.obscura.llc.moduleproject.data_source.CARD_USER
import com.obscura.llc.moduleproject.data_source.data.BaseCardModel
import com.obscura.llc.moduleproject.data_source.data.BaseModel
import com.obscura.llc.moduleproject.data_source.data.ItemClickListener
import com.obscura.llc.moduleproject.data_source.database.entity.UserEntity

/**
 *
 */
class UserCardModel(var user : UserEntity, private var listener: ItemClickListener<UserEntity>) : BaseCardModel() {
    override fun onClick() {
        listener.onClick(user)
    }

    fun getFullName(): String{
        return "${user.name} ${user.surname}"
    }

    fun getLocationToString(): String{
        return "${user.location?.country}, ${user.location?.state}, ${user.location?.city}"
    }

    override fun getCardId(): String {
        return user.id.toString()
    }

    override fun getCardType(): String {
        return CARD_USER
    }

    override fun getBaseModel(): BaseModel {
            return user
    }
}