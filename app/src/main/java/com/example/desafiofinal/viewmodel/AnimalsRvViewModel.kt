package com.example.desafiofinal.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafiofinal.RecyclerList
import com.example.desafiofinal.api.PostBody
import com.example.desafiofinal.api.RetroInstance
import com.example.desafiofinal.api.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimalsRvViewModel : ViewModel() {

    lateinit var recyclerListData: MutableLiveData<RecyclerList>

    init {
        recyclerListData = MutableLiveData()
    }

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

        post
    }
}

//@SerializedName("name")
//val name: String,
//@SerializedName("description")
//val description: String,
//@SerializedName("age")
//val age: Int,
//@SerializedName("species")
//val specie: String,
//@SerializedName("image")
//val image: String

//Recuperar as informações do usuário digitadas na tela de cadastro de animal
//Vai chamar a função que esta na viewmodel passando essas informações por parametro
//Utilizar as informações recebidas na viewmodel para criar o PostBody()
//COm isso é só passar o body na requisição