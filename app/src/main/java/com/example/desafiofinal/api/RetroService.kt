package com.example.desafiofinal.api

import com.example.desafiofinal.RecyclerList
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetroService {

    //https://bootcamp-ios-api.herokuapp.com/api/v1/animals

    @GET("/api/v1/animals")
    fun getDataFromApi(@Query("animals") query: String): Call<RecyclerList>

    @POST("/api/v1/animals")
    fun postDataInApi(@Body body: PostBody): Call<PostBody>
}