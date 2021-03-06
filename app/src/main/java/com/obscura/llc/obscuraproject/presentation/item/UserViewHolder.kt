package com.obscura.llc.obscuraproject.presentation.item

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.obscura.llc.moduleproject.data_source.database.entity.UserEntity

class UserViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private var userEntity: UserEntity? = null
    private var listener: IUserItemClickListener<UserEntity>? = null
    private val itemDetail = View.OnClickListener { listener!!.openDetail(this.userEntity!!) }

    fun bind(user: UserEntity, listener: IUserItemClickListener<UserEntity>) {
        userEntity = user
        this.listener = listener
        setupItem()
    }

    private fun setupItem() {
//        view.txtRvId.text = userEntity?.id.toString()
//        view.txtName.text = userEntity?.name
//        view.txtSurname.text = userEntity?.surname
//        view.txtFathername.text = userEntity?.fathername
        view.setOnClickListener(itemDetail)
    }
}
