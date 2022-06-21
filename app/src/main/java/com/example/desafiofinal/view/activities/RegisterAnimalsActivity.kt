package com.example.desafiofinal.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.desafiofinal.R
import com.example.desafiofinal.databinding.ActivityRegisterAnimalsBinding
import com.example.desafiofinal.viewmodel.AnimalsRvViewModel

class RegisterAnimalsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterAnimalsBinding
    private lateinit var viewModel: AnimalsRvViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterAnimalsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // starting view model on this activity:::

        viewModel = ViewModelProvider(this).get(AnimalsRvViewModel::class.java)

        onClick()

        // calling the bottom nav bar and the toolbar:::
        setUpBottomNavBar()
        setSupportActionBar(binding.toolbar)

    }

    private fun onClick() {
        binding.apply {
            btnRegister.setOnClickListener {
                viewModel.makePostApi(
                    name = binding.animalName.text.toString(),
                    description = binding.animalDescriptionReg.text.toString(),
                    age = binding.animalAge.text.toString().toInt(),
                    image = binding.animalLink.text.toString(),
                    specie = binding.animalSpecie.text.toString()
                )
                Toast.makeText(
                    this@RegisterAnimalsActivity,
                    "API GOT IT!", Toast.LENGTH_LONG
                ).show()
            }

        }
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