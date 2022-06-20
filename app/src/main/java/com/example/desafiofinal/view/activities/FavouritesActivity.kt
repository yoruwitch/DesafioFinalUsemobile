package com.example.desafiofinal.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.desafiofinal.R
import com.example.desafiofinal.databinding.ActivityFavouritesBinding
import com.example.desafiofinal.databinding.ActivityHomeBinding

class FavouritesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavouritesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // calling the bottom nav bar and the toolbar:::
        setUpBottomNavBar()
        setSupportActionBar(binding.toolbar)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

    }

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
                    val intent = Intent(this@FavouritesActivity, RegisterAnimalsActivity::class.java)
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