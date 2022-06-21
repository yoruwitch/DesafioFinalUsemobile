package com.example.desafiofinal

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class RecyclerList(val items: ArrayList<RecyclerData>)

@Parcelize
data class RecyclerData(

    val id: String?,
    val name: String?,
    val description: String?,
    val age: Int?,
    val species: String?,
    val image: String?
    ) : Parcelable


//"items": [
//{
//    "id": "fb0abbe2-a87a-4c2f-a2d4-364486e5a828",
//    "name": "Dory",
//    "description": "Peixe de Procurando o Nemo",
//    "age": 10,
//    "species": "Peixe",
//    "image": "https://zonacuriosa.com/wp-content/uploads/2021/12/que-tipo-de-peixe-e-a-dory-de-procurando-nemo.jpg",
//    "created_at": "2022-06-20T13:16:03.932Z",
//    "updated_at": "2022-06-20T13:16:03.932Z"
//},