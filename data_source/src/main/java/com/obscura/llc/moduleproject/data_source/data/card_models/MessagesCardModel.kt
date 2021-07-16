package com.obscura.llc.moduleproject.data_source.data.card_models

import com.obscura.llc.moduleproject.data_source.CARD_MESSAGES
import com.obscura.llc.moduleproject.data_source.data.BaseCardModel
import com.obscura.llc.moduleproject.data_source.data.BaseModel
import com.obscura.llc.moduleproject.data_source.data.ItemClickListener
import com.obscura.llc.moduleproject.data_source.database.entity.MessagesEntity

/**
 *
 */
class MessagesCardModel(var messages : MessagesEntity, private var listener: ItemClickListener<MessagesEntity>) : BaseCardModel() {
    override fun onClick() {
        listener.onClick(messages)
    }

    override fun getCardId(): String {
        return messages.id.toString()
    }

    override fun getCardType(): String {
        return CARD_MESSAGES
    }

    override fun getBaseModel(): BaseModel {
            return messages
    }
}