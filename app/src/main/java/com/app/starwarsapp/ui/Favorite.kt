package com.app.starwarsapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.app.starwarsapp.R
import com.app.starwarsapp.data.local.CharacterRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

lateinit var btnBackBottom: Button
lateinit var btnBackBackTop: FloatingActionButton
lateinit var list: ListView
lateinit var noFavorite: TextView

class FavoriteActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_favorite)
    setupView()
    setupListeners()
    setupList()

  }

  fun setupList() {
    val repository = CharacterRepository(this)
    val CharacterList = repository.getAll()

    if (CharacterList.isEmpty()) {
      noFavorite.visibility = View.VISIBLE
      list.visibility = View.GONE
      return
    }

    noFavorite.visibility = View.GONE

    val characterDataList = mutableListOf<String>()

    CharacterList.forEach { character ->
      val characterData = """
            Nome: ${character.name}
            Altura: ${character.height}
            Cor do Cabelo: ${character.hair_color}
            Cor da Pele: ${character.skin_color}
            Cor dos Olhos: ${character.eye_color}
            Ano de Nascimento: ${character.birth_year}
            Gênero: ${character.gender}
            Planeta Natal: ${character.homeworld}
            Peso: ${character.mass}
            URL: ${character.url}
            ID: ${character.id}
            --------------------
        """.trimIndent()

      characterDataList.add(characterData)
    }

    val adapter = ArrayAdapter(this, R.layout.list_item, R.id.text_view, characterDataList)
    list.adapter = adapter
  }

  fun setupView() {
    btnBackBottom = findViewById(R.id.btnBackBottom)
    btnBackBackTop = findViewById(R.id.btnBackTop)
    list = findViewById(R.id.lista)
    noFavorite = findViewById(R.id.noFavorite)

  }

  fun setupListeners() {
    btnBackBottom.setOnClickListener {
      startActivity(Intent(this, SelectorActivity::class.java))
    }

    btnBackBackTop.setOnClickListener {
      startActivity(Intent(this, SelectorActivity::class.java))
    }
  }
}