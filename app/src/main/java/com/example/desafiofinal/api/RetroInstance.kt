package com.example.desafiofinal.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object{
        val baseUrl = "https://bootcamp-ios-api.herokuapp.com"

        fun getRetroInstance(): Retrofit{

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}