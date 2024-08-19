package com.example.starwarsapp.data.local

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import android.widget.Toast
import com.example.starwarsapp.data.local.CharacterContract.CharacterEntry.COLUMN_NAME_ID
import com.example.starwarsapp.data.local.CharacterContract.CharacterEntry.COLUMN_NAME_NAME
import com.example.starwarsapp.data.local.CharacterContract.CharacterEntry.TABLE_NAME
import com.example.starwarsapp.domain.Character

class CharacterRepository(val context: Context) {
  fun save(character: Character): Boolean {
    var isSaved = false
    try {

      val dbHelper = CharacterDbHelper(context)
      val db = dbHelper.writableDatabase
      val values = ContentValues().apply {
        put(CharacterContract.CharacterEntry.COLUMN_NAME_ID, character.id)
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
      if (inserted != null) {
        isSaved = true
        Log.e("Inserted", inserted.toString())
      }
    } catch (ex: Exception) {
      ex.message?.let { Log.e("Error", it) }
    }
    return isSaved
  }

  fun findCharacterById(id: Int): Character {
    val dbHelper = CharacterDbHelper(context)
    val db = dbHelper.writableDatabase
    val columns = arrayOf(
      BaseColumns._ID,
      CharacterContract.CharacterEntry.COLUMN_NAME_ID,
      CharacterContract.CharacterEntry.COLUMN_NAME_NAME,
      CharacterContract.CharacterEntry.COLUMN_NAME_HEIGHT,
      CharacterContract.CharacterEntry.COLUMN_NAME_MASS,
      CharacterContract.CharacterEntry.COLUMN_NAME_HAIR_COLOR,
      CharacterContract.CharacterEntry.COLUMN_NAME_SKIN_COLOR,
      CharacterContract.CharacterEntry.COLUMN_NAME_EYE_COLOR,
      CharacterContract.CharacterEntry.COLUMN_NAME_BIRTH_YEAR,
      CharacterContract.CharacterEntry.COLUMN_NAME_GENDER,
      CharacterContract.CharacterEntry.COLUMN_NAME_HOMEWORLD,
      CharacterContract.CharacterEntry.COLUMN_NAME_CREATED,
      CharacterContract.CharacterEntry.COLUMN_NAME_EDITED,
      CharacterContract.CharacterEntry.COLUMN_NAME_URL
    )

    val selection = "${BaseColumns._ID} = ?"
    val filter = arrayOf(id.toString())
    val cursor = db.query(
      CharacterContract.CharacterEntry.TABLE_NAME,
      columns,
      selection,
      filter,
      null,
      null,
      null
    )
    var characterId: Long = 0
    var name: String = ""
    var height: String = ""
    var mass: String = ""
    var hairColor: String = ""
    var skinColor: String = ""
    var eyeColor: String = ""
    var birthYear: String = ""
    var gender: String = ""
    var homeWorld: String = ""
    var created: String = ""
    var edited: String = ""
    var url: String = ""


    with(cursor) {
      while (moveToNext()) {
        characterId = getLong(getColumnIndexOrThrow(COLUMN_NAME_ID))
        name = getString(getColumnIndexOrThrow(COLUMN_NAME_NAME))
        height =
          getString(getColumnIndexOrThrow(CharacterContract.CharacterEntry.COLUMN_NAME_HEIGHT))
        mass = getString(getColumnIndexOrThrow(CharacterContract.CharacterEntry.COLUMN_NAME_MASS))
        hairColor =
          getString(getColumnIndexOrThrow(CharacterContract.CharacterEntry.COLUMN_NAME_HAIR_COLOR))
        skinColor =
          getString(getColumnIndexOrThrow(CharacterContract.CharacterEntry.COLUMN_NAME_SKIN_COLOR))
        eyeColor =
          getString(getColumnIndexOrThrow(CharacterContract.CharacterEntry.COLUMN_NAME_EYE_COLOR))
        birthYear =
          getString(getColumnIndexOrThrow(CharacterContract.CharacterEntry.COLUMN_NAME_BIRTH_YEAR))
        gender =
          getString(getColumnIndexOrThrow(CharacterContract.CharacterEntry.COLUMN_NAME_GENDER))
        homeWorld =
          getString(getColumnIndexOrThrow(CharacterContract.CharacterEntry.COLUMN_NAME_HOMEWORLD))
        created =
          getString(getColumnIndexOrThrow(CharacterContract.CharacterEntry.COLUMN_NAME_CREATED))
        edited =
          getString(getColumnIndexOrThrow(CharacterContract.CharacterEntry.COLUMN_NAME_EDITED))
        url = getString(getColumnIndexOrThrow(CharacterContract.CharacterEntry.COLUMN_NAME_URL))
      }
    }
    cursor.close()
    return Character(
      id = characterId.toInt(),
      name = name,
      height = height,
      mass = mass,
      hair_color = hairColor,
      skin_color = skinColor,
      eye_color = eyeColor,
      birth_year = birthYear,
      gender = gender,
      homeworld = homeWorld,
      films = emptyList(),
      species = emptyList(),
      vehicles = emptyList(),
      starships = emptyList(),
      created = created,
      edited = edited,
      url = url
    )
  }

  fun saveIfNotExist(character: Character) {
    val personDb = getAll()
    val personDbId = personDb.map { it.id }

    if (character.id in personDbId) {
      Toast.makeText(context, "Personagem já favoritado!", Toast.LENGTH_SHORT).show()
      return
    }
    // Caso não exista, salva no DB e exibe mensagem de sucesso
    save(character)
    Toast.makeText(context, "Favorito adicionado!", Toast.LENGTH_SHORT).show()
  }


  fun getAll(): List<Character> {
    val dbHelper = CharacterDbHelper(context)
    val db = dbHelper.writableDatabase
    val columns = arrayOf(
      BaseColumns._ID,
      CharacterContract.CharacterEntry.COLUMN_NAME_ID,
      CharacterContract.CharacterEntry.COLUMN_NAME_NAME,
      CharacterContract.CharacterEntry.COLUMN_NAME_HEIGHT,
      CharacterContract.CharacterEntry.COLUMN_NAME_MASS,
      CharacterContract.CharacterEntry.COLUMN_NAME_HAIR_COLOR,
      CharacterContract.CharacterEntry.COLUMN_NAME_SKIN_COLOR,
      CharacterContract.CharacterEntry.COLUMN_NAME_EYE_COLOR,
      CharacterContract.CharacterEntry.COLUMN_NAME_BIRTH_YEAR,
      CharacterContract.CharacterEntry.COLUMN_NAME_GENDER,
      CharacterContract.CharacterEntry.COLUMN_NAME_HOMEWORLD,
      CharacterContract.CharacterEntry.COLUMN_NAME_CREATED,
      CharacterContract.CharacterEntry.COLUMN_NAME_EDITED,
      CharacterContract.CharacterEntry.COLUMN_NAME_URL
    )

    val cursor = db.query(
      CharacterContract.CharacterEntry.TABLE_NAME,
      columns,
      null,
      null,
      null,
      null,
      null
    )

    val character = mutableListOf<Character>()


    with(cursor) {
      while (moveToNext()) {
        val characterId = getLong(getColumnIndexOrThrow(COLUMN_NAME_ID))
        val name = getString(getColumnIndexOrThrow(COLUMN_NAME_NAME))
        val height =
          getString(getColumnIndexOrThrow(CharacterContract.CharacterEntry.COLUMN_NAME_HEIGHT))
        val mass =
          getString(getColumnIndexOrThrow(CharacterContract.CharacterEntry.COLUMN_NAME_MASS))
        val hairColor =
          getString(getColumnIndexOrThrow(CharacterContract.CharacterEntry.COLUMN_NAME_HAIR_COLOR))
        val skinColor =
          getString(getColumnIndexOrThrow(CharacterContract.CharacterEntry.COLUMN_NAME_SKIN_COLOR))
        val eyeColor =
          getString(getColumnIndexOrThrow(CharacterContract.CharacterEntry.COLUMN_NAME_EYE_COLOR))
        val birthYear =
          getString(getColumnIndexOrThrow(CharacterContract.CharacterEntry.COLUMN_NAME_BIRTH_YEAR))
        val gender =
          getString(getColumnIndexOrThrow(CharacterContract.CharacterEntry.COLUMN_NAME_GENDER))
        val homeWorld =
          getString(getColumnIndexOrThrow(CharacterContract.CharacterEntry.COLUMN_NAME_HOMEWORLD))
        val created =
          getString(getColumnIndexOrThrow(CharacterContract.CharacterEntry.COLUMN_NAME_CREATED))
        val edited =
          getString(getColumnIndexOrThrow(CharacterContract.CharacterEntry.COLUMN_NAME_EDITED))
        val url = getString(getColumnIndexOrThrow(CharacterContract.CharacterEntry.COLUMN_NAME_URL))

        character.add(
          Character(
            id = characterId.toInt(),
            name = name,
            height = height,
            mass = mass,
            hair_color = hairColor,
            skin_color = skinColor,
            eye_color = eyeColor,
            birth_year = birthYear,
            gender = gender,
            homeworld = homeWorld,
            films = emptyList(),
            species = emptyList(),
            vehicles = emptyList(),
            starships = emptyList(),
            created = created,
            edited = edited,
            url = url
          )
        )
      }
    }
    cursor.close()
    return character
  }
}
