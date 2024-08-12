package com.example.starwarsapp.data

import com.example.starwarsapp.domain.Character
import retrofit2.Call
import retrofit2.http.GET

interface CharacterApi {
  @GET("?format=json")
  fun getAllCharacters() : Call<List<Character>>
}