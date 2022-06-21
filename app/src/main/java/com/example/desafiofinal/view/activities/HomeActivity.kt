package com.example.desafiofinal.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.desafiofinal.R
import com.example.desafiofinal.RecyclerList
import com.example.desafiofinal.databinding.ActivityHomeBinding
import com.example.desafiofinal.view.activities.recyclerview.AnimalsRvAdapter
import com.example.desafiofinal.viewmodel.AnimalsRvViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerViewAdapter: AnimalsRvAdapter
    private lateinit var viewModel: AnimalsRvViewModel
    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // calling the viewmodel

        viewModel = ViewModelProvider(this).get(AnimalsRvViewModel::class.java)

        // calling the bottom nav bar and the toolbar:::
        setUpBottomNavBar()
        setSupportActionBar(binding.toolbar)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        // Recycler View:::
        initRecyclerView()
        createData()
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            recyclerViewAdapter = AnimalsRvAdapter()
            adapter = recyclerViewAdapter

            val decoration = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(decoration)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun createData() {
        viewModel.getDataFromRecyclerListObserver().observe(this, Observer<RecyclerList> {

            if (it != null) {
                recyclerViewAdapter.setData(it.items)
                recyclerViewAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(
                    this@HomeActivity,
                    "Error in getting data from api",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
        viewModel.makeApiCall()
    }

    private fun setUpBottomNavBar() {
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.favourites -> {
                    val intent = Intent(this, FavouritesActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.addAnimal -> {
                    val intent = Intent(this, RegisterAnimalsActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}