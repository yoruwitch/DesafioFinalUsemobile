package com.example.desafiofinal.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiofinal.RecyclerList
import com.example.desafiofinal.api.PostBody
import com.example.desafiofinal.api.RetroInstance
import com.example.desafiofinal.api.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimalsRvViewModel : ViewModel() {

    var recyclerListData: MutableLiveData<RecyclerList> = MutableLiveData()

    fun getDataFromRecyclerListObserver(): MutableLiveData<RecyclerList> {
        return recyclerListData
    }

    fun makeApiCall() {
        val retroInstance =
            RetroInstance.getRetroInstance().create(RetroService::class.java)

        val call = retroInstance.getDataFromApi("animals")
        call.enqueue(object : Callback<RecyclerList> {

            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if (response.isSuccessful) {

                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {

                recyclerListData.postValue(null)
            }
        })
    }

    fun makePostApi(
        name: String, description: String,
        age: Int, specie: String, image: String
    ) {
        val retroInstance =
            RetroInstance.getRetroInstance().create(RetroService::class.java)
        val post = retroInstance.postDataInApi(PostBody(name, description, age, specie, image))

        post.enqueue(object : Callback<PostBody> {
            override fun onResponse(call: Call<PostBody>, response: Response<PostBody>) {
            }

            override fun onFailure(call: Call<PostBody>, t: Throwable) {
            }

        })
    }

    fun favourites(){
        
    }

}




