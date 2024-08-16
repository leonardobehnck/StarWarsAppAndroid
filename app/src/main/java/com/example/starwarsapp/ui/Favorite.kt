package com.example.starwarsapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.starwarsapp.R
import com.example.starwarsapp.data.CharacterApi
import com.example.starwarsapp.domain.Character
import com.google.android.material.floatingactionbutton.FloatingActionButton

lateinit var btnBackBottom: Button
lateinit var btnBackbtnBackTop: FloatingActionButton
lateinit var list: ListView

class FavoriteActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_favorite)
    var model : String? = intent.getStringExtra("model")
    setupView()
    setupListeners()
    model?.let { setupList(it) }
  }

  fun setupList(model: String) {
    Log.d("teste", model)
//    val modelToArrayList = arrayOf(
//      "Nome: ${model.name}",
//      "Altura: ${model.height}",
//      "Cor do Cabelo: ${model.hair_color}",
//      "Cor da Pele: ${model.skin_color}",
//      "Cor dos Olhos: ${model.eye_color}",
//      "Ano de Nascimento: ${model.birth_year}",
//      "Gênero: ${model.gender}",
//      "Planeta Natal: ${model.homeworld}",
//      "Peso: ${model.mass}",
//      "Filmes:${model.films}",
//      "Especies: ${model.species}",
//      "Veículos: ${model.vehicles}",
//      "Naves Espaciais: ${model.starships}",
//      "URL: ${model.url}"
//    )

    //val adapter = ArrayAdapter(this, R.layout.list_item, R.id.text_view, modelToArrayList)
    //this.list.adapter = adapter
  }

  fun setupView() {
    btnBackBottom = findViewById(R.id.btnBackBottom)
    btnBackbtnBackTop = findViewById(R.id.btnBackTop)
    list = findViewById(R.id.lista)

  }
  fun setupListeners() {
    btnBackBottom.setOnClickListener {
      startActivity(Intent(this, SelectorActivity::class.java))
    }

    btnBackbtnBackTop.setOnClickListener {
      startActivity(Intent(this, SelectorActivity::class.java))
    }
  }
}