package com.example.myapplication.ui.main.view

import android.graphics.Canvas
import android.os.Bundle
import android.view.View.*
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.model.Friend
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.main.adapter.MainAdapter
import com.example.myapplication.ui.main.viewmodel.MainViewModel
import com.example.myapplication.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.friends.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = GONE
                    it.data?.let { friend -> renderList(friend) }
                    binding.recyclerView.visibility = VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = VISIBLE
                    binding.recyclerView.visibility = GONE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(friends: Friend) {
        adapter.addData(friends.results)
        adapter.notifyDataSetChanged()
    }

}