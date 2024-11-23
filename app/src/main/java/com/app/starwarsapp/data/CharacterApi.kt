package com.app.starwarsapp.data

import retrofit2.Call
import retrofit2.http.GET

interface CharacterApi {
  @GET("people/?format=json")
  fun getAllCharacters() : Call<CharacterWrapper>
}