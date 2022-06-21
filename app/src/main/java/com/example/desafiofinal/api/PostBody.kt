package com.example.desafiofinal.api

import com.google.gson.annotations.SerializedName

data class PostBody(

    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("species")
    val specie: String,
    @SerializedName("image")
    val image: String
)
