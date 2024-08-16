package com.example.starwarsapp.data.local

import android.provider.BaseColumns

object CharacterContract {
  object CharacterEntry : BaseColumns {
    const val TABLE_NAME = "character"
    const val COLUMN_NAME_NAME = "name"
    const val COLUMN_NAME_HEIGHT = "height"
    const val COLUMN_NAME_MASS = "mass"
    const val COLUMN_NAME_HAIR_COLOR = "hair_color"
    const val COLUMN_NAME_SKIN_COLOR = "skin_color"
    const val COLUMN_NAME_EYE_COLOR = "eye_color"
    const val COLUMN_NAME_BIRTH_YEAR = "birth_year"
    const val COLUMN_NAME_GENDER = "gender"
    const val COLUMN_NAME_HOMEWORLD = "homeworld"
    const val COLUMN_NAME_FILMS = "films"
    const val COLUMN_NAME_SPECIES = "species"
    const val COLUMN_NAME_VEHICLES = "vehicles"
    const val COLUMN_NAME_STARSHIPS = "starships"
    const val COLUMN_NAME_CREATED = "created"
    const val COLUMN_NAME_EDITED = "edited"
    const val COLUMN_NAME_URL = "url"
  }
}