package com.example.desafiofinal.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.desafiofinal.R
import com.example.desafiofinal.databinding.ActivityFavouritesBinding

class FavouritesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavouritesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

//         calling the bottom nav bar and the toolbar:::
        setUpBottomNavBar()
        setSupportActionBar(binding.toolbar)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

    }

//    private fun initRecyclerView() {
//        binding.recyclerView.apply {
//            recyclerViewAdapter = AnimalsRvAdapter(::onClickAdapter)
//            adapter = recyclerViewAdapter
//
//            addItemDecoration(
//                DividerItemDecoration(
//                    this@HomeActivity,
//                    LinearLayoutManager.VERTICAL
//                )
//            )
//
//        }
//        //TODO: Create this func in the viewModel:::
//        fun createData() {
//            viewModel.getFavouritesFromSharedPref.observe(this, Observer<RecyclerList> {
//
//                if (it != null) {
//                    recyclerViewAdapter.setData(it.items)
//                    recyclerViewAdapter.notifyDataSetChanged()
//                } else {
//                    Toast.makeText(
//                        this@HomeActivity,
//                        "Error in getting data from api",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//            })
//            viewModel.makeApiCall()
//        }

        private fun setUpBottomNavBar() {
            binding.bottomNav.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> {
                        val intent = Intent(this@FavouritesActivity, HomeActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    R.id.favourites -> {
                        val intent = Intent(this@FavouritesActivity, FavouritesActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    R.id.addAnimal -> {
                        val intent =
                            Intent(this@FavouritesActivity, RegisterAnimalsActivity::class.java)
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