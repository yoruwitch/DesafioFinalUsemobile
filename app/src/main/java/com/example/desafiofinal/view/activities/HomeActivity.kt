package com.example.desafiofinal.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.desafiofinal.R
import com.example.desafiofinal.RecyclerList
import com.example.desafiofinal.api.RetroInstance
import com.example.desafiofinal.api.RetroService
import com.example.desafiofinal.databinding.ActivityHomeBinding
import com.example.desafiofinal.view.activities.recyclerview.AnimalsRvAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerViewAdapter: AnimalsRvAdapter

    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        val retroInstance =
            RetroInstance.getRetroInstance().create(RetroService::class.java)

        val call = retroInstance.getDataFromApi("animals")
        call.enqueue(object : Callback<RecyclerList> {

            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if (response.isSuccessful) {
                    recyclerViewAdapter.setData(response.body()?.items!!)
                    recyclerViewAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                Toast.makeText(
                    this@HomeActivity,
                    "Error in getting data from api",
                    Toast.LENGTH_LONG
                ).show()
            }
        })


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