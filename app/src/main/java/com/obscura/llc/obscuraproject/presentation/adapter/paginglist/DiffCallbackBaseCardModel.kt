package com.obscura.llc.obscuraproject.presentation.adapter.paginglist

import androidx.recyclerview.widget.DiffUtil
import com.obscura.llc.moduleproject.data_source.data.BaseCardModel

/**
 *
 */
class DiffCallbackBaseCardModel : DiffUtil.ItemCallback<BaseCardModel>() {

    companion object {
        val CONTENT = Any()
        val REACTION = Any()
    }

    override fun areItemsTheSame(oldCard: BaseCardModel, newCard: BaseCardModel): Boolean {
        return oldCard.getCardId() == newCard.getCardId()
    }

    override fun areContentsTheSame(oldCard: BaseCardModel, newCard: BaseCardModel): Boolean {
        return oldCard.getCardId() == newCard.getCardId()
    }
}