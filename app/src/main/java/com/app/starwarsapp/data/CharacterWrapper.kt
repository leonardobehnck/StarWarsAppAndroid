package com.app.starwarsapp.data

import com.app.starwarsapp.domain.Character

data class CharacterWrapper(
  val count: Long,
  val next: String,
  val previous: Any?,
  val results: List<Character>,
)