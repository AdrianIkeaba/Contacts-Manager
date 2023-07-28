package com.ghostdev.contactsmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ghostdev.contactsmanager.room.User
import com.ghostdev.contactsmanager.room.UserDatabase
import com.ghostdev.contactsmanager.room.UserRepository
import com.ghostdev.contactsmanager.viewModel.UserViewModel
import com.ghostdev.contactsmanager.viewModel.UserViewModelFactory
import com.ghostdev.contactsmanager.viewUI.RecyclerViewAdapter
import com.ghostdev.contactsmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Room
        val dao = UserDatabase.getInstance(this).userDao
        val repository = UserRepository(dao)
        val factory = UserViewModelFactory(repository)

        userViewModel = ViewModelProvider(this, factory) [UserViewModel::class.java]
        binding.userViewModel = userViewModel

        binding.lifecycleOwner = this

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager  = LinearLayoutManager(this)
        displayUsersList()
    }

    private fun displayUsersList() {
        userViewModel.users.observe(this, Observer {
            binding.recyclerView.adapter = RecyclerViewAdapter(this, it) { selectedItem: User -> listItemClicked(selectedItem) }
        })
    }
    private fun listItemClicked(selectedItem: User) {
        Toast.makeText(applicationContext, "Selected contact is ${selectedItem.name}", Toast.LENGTH_LONG).show()

        userViewModel.initUpdateAndDelete(selectedItem)
    }
}