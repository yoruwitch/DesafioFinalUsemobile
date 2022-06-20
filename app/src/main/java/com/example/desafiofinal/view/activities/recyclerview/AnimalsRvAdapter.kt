package com.example.desafiofinal.view.activities.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafiofinal.R
import com.example.desafiofinal.RecyclerData
import com.example.desafiofinal.databinding.LayoutAnimalRowBinding

class AnimalsRvAdapter :
    RecyclerView.Adapter<AnimalsRvAdapter.MyViewHolder>() {

    // this list here will get all data from the api and link to the recycler view:::
    private var animalsList = ArrayList<RecyclerData>()

    fun setData(data: ArrayList<RecyclerData>) {
        this.animalsList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding = LayoutAnimalRowBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(animalsList[position])
    }

    override fun getItemCount(): Int {
        return animalsList.size
    }


    class MyViewHolder(private val itemBinding: LayoutAnimalRowBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        private val animalTitle = itemBinding.animalTitle
        private val animalDesc = itemBinding.animalDescription
        private val animalImg = itemBinding.animalImage

        fun onBind(data: RecyclerData) {

            animalTitle.text = data.name
            animalDesc.text = data.description

            val url = data.image
            Glide.with(animalImg)
                .load(url)
                .placeholder(R.drawable.juma_testing)
                .error(R.drawable.juma_testing)
                .fallback(R.drawable.juma_testing)
                .into(animalImg)

        }
    }
}