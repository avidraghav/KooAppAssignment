package com.example.kooappassignment.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kooappassignment.databinding.ActivityMainBinding
import com.example.kooappassignment.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = ViewModelProvider(this).get(AppViewModel::class.java)

        setupRecyclerView()

        viewModel.productListResponse.observe(this) { resource ->
            when (resource) {
                Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Failure -> showErrorMessage()
                is Resource.Success -> {
                    hideProgressBar()
                    postAdapter.differ.submitList(resource.value.data)
                }
            }
        }

    }

    private fun showErrorMessage() {
        hideProgressBar()
        Toast.makeText(this, "Error Occurred", Toast.LENGTH_SHORT).show()
    }

    private fun setupRecyclerView() {
        postAdapter = PostAdapter()
        binding.recyclerView.apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }
}