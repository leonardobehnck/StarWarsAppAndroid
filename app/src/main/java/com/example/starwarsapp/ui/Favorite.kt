package com.example.starwarsapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.starwarsapp.R
import com.example.starwarsapp.data.local.CharacterRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

lateinit var btnBackBottom: Button
lateinit var btnBackBackTop: FloatingActionButton
lateinit var list: ListView

class FavoriteActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_favorite)
    setupView()
    setupListeners()
    setupList()
  }

  fun setupList() {
    val model = CharacterRepository(this).findCharacterById(4)
    val modelToArrayList = arrayOf(
      "ID: ${model.id}",
      "Nome: ${model.name}",
      "Altura: ${model.height}",
      "Cor do Cabelo: ${model.hair_color}",
      "Cor da Pele: ${model.skin_color}",
      "Cor dos Olhos: ${model.eye_color}",
      "Ano de Nascimento: ${model.birth_year}",
      "Gênero: ${model.gender}",
      "Planeta Natal: ${model.homeworld}",
      "Peso: ${model.mass}",
      "URL: ${model.url}"
    )

    val adapter = ArrayAdapter(this, R.layout.list_item, R.id.text_view, modelToArrayList)
    list.adapter = adapter
  }


  fun setupView() {
    btnBackBottom = findViewById(R.id.btnBackBottom)
    btnBackBackTop = findViewById(R.id.btnBackTop)
    list = findViewById(R.id.lista)

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