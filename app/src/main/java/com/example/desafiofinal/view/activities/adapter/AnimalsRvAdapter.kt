package com.example.desafiofinal.view.activities.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafiofinal.R
import com.example.desafiofinal.RecyclerData

class AnimalsRvAdapter(private val onClick: (RecyclerData) -> Unit) :
    RecyclerView.Adapter<AnimalsRvAdapter.MyViewHolder>() {

    // this list here will get all data from the api and link to the recycler view:::
    private var animalsList = ArrayList<RecyclerData>()

    fun setData(data: ArrayList<RecyclerData>) {
        this.animalsList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(
            parent.context
        ).inflate(R.layout.layout_animal_row, parent, false)
        return MyViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(animalsList[position])
    }

    override fun getItemCount(): Int {
        return animalsList.size
    }

    class MyViewHolder(
        itemView: View,
        private val onClick: (RecyclerData) -> Unit
    ) :
        RecyclerView.ViewHolder(itemView) {

        private val animalTitle = itemView.findViewById<TextView>(R.id.animal_title)
        private val animalDesc = itemView.findViewById<TextView>(R.id.animal_description)
        private val animalImg = itemView.findViewById<ImageView>(R.id.animal_image)

        fun onBind(data: RecyclerData) {

            // to click the item and see the details:::

            itemView.setOnClickListener {
                onClick(data)
            }
            animalTitle.text = data.name
            animalDesc.text = data.description

            val url = data.image
            Glide.with(animalImg)
                .load(url)
                .placeholder(R.drawable.juma_testing)
                .error(R.drawable.juma_testing)
                .fallback(R.drawable.juma_testing)
                .into(animalImg)


            // Favorite button:::
            val addFavourite = itemView.findViewById<ImageView>(R.id.btn_favorite)
            var gambiarra = 1
            addFavourite.setOnClickListener {
                gambiarra++
                if (gambiarra % 2 == 0) {
                    addFavourite.setImageResource(R.drawable.star_favourite)
                } else {
                    addFavourite.setImageResource(R.drawable.star)
                }


            }

        }
    }
}
