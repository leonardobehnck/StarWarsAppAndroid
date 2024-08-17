package com.example.starwarsapp.data.local

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.starwarsapp.data.local.CharacterContract.CharacterEntry.TABLE_NAME
import com.example.starwarsapp.domain.Character

class CharacterRepository(val context: Context) {
    @SuppressLint("SuspiciousIndentation")
    fun save(character: Character): Boolean {
      var isSaved = false
      try {
        val dbHelper = CharacterDbHelper(context)
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
          put(CharacterContract.CharacterEntry.COLUMN_NAME_NAME, character.name)
          put(CharacterContract.CharacterEntry.COLUMN_NAME_HEIGHT, character.height)
          put(CharacterContract.CharacterEntry.COLUMN_NAME_MASS, character.mass)
          put(CharacterContract.CharacterEntry.COLUMN_NAME_HAIR_COLOR, character.hair_color)
          put(CharacterContract.CharacterEntry.COLUMN_NAME_SKIN_COLOR, character.skin_color)
          put(CharacterContract.CharacterEntry.COLUMN_NAME_EYE_COLOR, character.eye_color)
          put(CharacterContract.CharacterEntry.COLUMN_NAME_BIRTH_YEAR, character.birth_year)
          put(CharacterContract.CharacterEntry.COLUMN_NAME_GENDER, character.gender)
          put(CharacterContract.CharacterEntry.COLUMN_NAME_HOMEWORLD, character.homeworld)
          //put(CharacterContract.CharacterEntry.COLUMN_NAME_FILMS, character.films)
          //put(CharacterContract.CharacterEntry.COLUMN_NAME_SPECIES, character.species)
          //put(CharacterContract.CharacterEntry.COLUMN_NAME_VEHICLES, character.vehicles)
          //put(CharacterContract.CharacterEntry.COLUMN_NAME_STARSHIPS, character.starships)
          put(CharacterContract.CharacterEntry.COLUMN_NAME_CREATED, character.created)
          put(CharacterContract.CharacterEntry.COLUMN_NAME_EDITED, character.edited)
          put(CharacterContract.CharacterEntry.COLUMN_NAME_URL, character.url)
      }
      val inserted = db.insert(TABLE_NAME, null, values)
        if(inserted != null) {
          isSaved = true
          Log.e("Inserted", inserted.toString())
        }
      } catch (ex: Exception) {
      ex.message?.let { Log.e("Error", it) }
    }
      return isSaved
  }
}