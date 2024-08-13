package com.example.starwarsapp.data

import com.example.starwarsapp.domain.Character
import retrofit2.Call
import retrofit2.http.GET

interface CharacterApi {
  @GET("people/?format=json")
  fun getAllCharacters() : Call<CharacterWrapper>
}