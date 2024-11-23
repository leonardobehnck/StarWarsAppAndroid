package com.app.starwarsapp.data.local

import android.provider.BaseColumns

object CharacterContract {
  const val DATABASE_NAME = "DbCharacter.db"
  const val DATABASE_VERSION = 1

  object CharacterEntry : BaseColumns {
    const val TABLE_NAME = "character"
    const val COLUMN_NAME_ID = "id"
    const val COLUMN_NAME_NAME = "name"
    const val COLUMN_NAME_HEIGHT = "height"
    const val COLUMN_NAME_MASS = "mass"
    const val COLUMN_NAME_HAIR_COLOR = "hair_color"
    const val COLUMN_NAME_SKIN_COLOR = "skin_color"
    const val COLUMN_NAME_EYE_COLOR = "eye_color"
    const val COLUMN_NAME_BIRTH_YEAR = "birth_year"
    const val COLUMN_NAME_GENDER = "gender"
    const val COLUMN_NAME_HOMEWORLD = "homeworld"
    //const val COLUMN_NAME_FILMS = "films"
    //const val COLUMN_NAME_SPECIES = "species"
    //const val COLUMN_NAME_VEHICLES = "vehicles"
    //const val COLUMN_NAME_STARSHIPS = "starships"
    const val COLUMN_NAME_CREATED = "created"
    const val COLUMN_NAME_EDITED = "edited"
    const val COLUMN_NAME_URL = "url"
  }
  const val TABLE_CHARACTER =
    "CREATE TABLE ${CharacterEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${CharacterEntry.COLUMN_NAME_ID} TEXT," +
            "${CharacterEntry.COLUMN_NAME_NAME} TEXT," +
            "${CharacterEntry.COLUMN_NAME_HEIGHT} TEXT," +
            "${CharacterEntry.COLUMN_NAME_MASS} TEXT," +
            "${CharacterEntry.COLUMN_NAME_HAIR_COLOR} TEXT," +
            "${CharacterEntry.COLUMN_NAME_SKIN_COLOR} TEXT," +
            "${CharacterEntry.COLUMN_NAME_EYE_COLOR} TEXT," +
            "${CharacterEntry.COLUMN_NAME_BIRTH_YEAR} TEXT," +
            "${CharacterEntry.COLUMN_NAME_GENDER} TEXT," +
            "${CharacterEntry.COLUMN_NAME_HOMEWORLD} TEXT," +
            //"${CharacterEntry.COLUMN_NAME_FILMS} LIST" +
            //"${CharacterEntry.COLUMN_NAME_SPECIES} LIST<TEXT>," +
            //"${CharacterEntry.COLUMN_NAME_VEHICLES} TEXT," +
            //"${CharacterEntry.COLUMN_NAME_STARSHIPS} TEXT," +
            "${CharacterEntry.COLUMN_NAME_CREATED} TEXT," +
            "${CharacterEntry.COLUMN_NAME_EDITED} TEXT," +
            "${CharacterEntry.COLUMN_NAME_URL} TEXT)"

  const val SQL_DELETE_ENTRIES =
    "DROP TABLE IF EXISTS ${CharacterEntry.TABLE_NAME}"
}