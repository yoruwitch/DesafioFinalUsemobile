package com.example.desafiofinal.view.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.desafiofinal.R
import com.example.desafiofinal.RecyclerData
import com.example.desafiofinal.databinding.ActivityAnimalDetailsBinding

class AnimalDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimalDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sets top bar and bottom nav bar
        setSupportActionBar(binding.toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        //Getting the data from the click in the HomeActivity and setting in the interface:::

        val extras = intent.extras
        val data: RecyclerData? = extras?.getParcelable("Input click")
        data?.let {
            binding.animalNameAge.text = "${it.name} - ${it.age} anos"
            binding.animalCompleteDesc.text = it.description
            binding.animalSpecieDetails.text = it.species

            val url = data.image
            Glide.with(binding.animalImgDetails)
                .load(url)
                .placeholder(R.drawable.juma_testing)
                .error(R.drawable.juma_testing)
                .fallback(R.drawable.juma_testing)
                .into(binding.animalImgDetails)
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