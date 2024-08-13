package com.example.starwarsapp.data

import com.example.starwarsapp.domain.Character

data class CharacterWrapper(
  val count: Long,
  val next: String,
  val previous: Any?,
  val results: List<Character>,
)