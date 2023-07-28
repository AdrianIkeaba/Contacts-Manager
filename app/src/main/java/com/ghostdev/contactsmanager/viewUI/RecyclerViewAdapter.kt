package com.ghostdev.contactsmanager.viewUI

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ghostdev.contactsmanager.R
import com.ghostdev.contactsmanager.room.User
import com.ghostdev.contactsmanager.databinding.CardItemBinding

class RecyclerViewAdapter(private val context: Context, private val userList: List<User>, private val clickListener: (User) -> Unit): RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CardItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.card_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(userList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}

class MyViewHolder(private val binding: CardItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User, clickListener: (User) -> Unit) {
        binding.nameText.text = user.name
        binding.emailText.text = user.email

        binding.listItemLayout.setOnClickListener {
            clickListener(user)
        }
    }

}