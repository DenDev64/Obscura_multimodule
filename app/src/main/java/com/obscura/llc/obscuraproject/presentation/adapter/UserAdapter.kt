package com.obscura.llc.obscuraproject.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.obscura.llc.obscuraproject.R
import com.obscura.llc.obscuraproject.presentation.base.BaseClickableAdapter
import com.obscura.llc.obscuraproject.presentation.item.IUserItemClickListener
import com.obscura.llc.obscuraproject.presentation.item.UserViewHolder
import com.obscura.llc.moduleproject.data_source.database.entity.UserEntity

class UserAdapter(private val context: Context, private val users: List<UserEntity>, private val listener: IUserItemClickListener<UserEntity>) :
    BaseClickableAdapter<UserViewHolder, UserEntity, IUserItemClickListener<UserEntity>>(users as MutableList<UserEntity>, listener) {

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(userViewHolder: UserViewHolder, i: Int) {
        super.onBindViewHolder(userViewHolder, i)
        userViewHolder.bind(users[i], listener)
    }
}
