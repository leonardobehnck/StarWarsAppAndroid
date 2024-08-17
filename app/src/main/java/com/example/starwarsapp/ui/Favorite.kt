package com.example.starwarsapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.starwarsapp.R
import com.example.starwarsapp.data.local.CharacterDbHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton

lateinit var btnBackBottom: Button
lateinit var btnBackbtnBackTop: FloatingActionButton
lateinit var list: ListView

class FavoriteActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_favorite)
    setupView()
    setupListeners()
    //setupList()
  }

  fun getSharedPref(): String {
    val sharedPref = getPreferences(Context.MODE_PRIVATE)
    return sharedPref.getString(getString(R.string.saved_character), "empty").toString()
  }

//  fun setupList(model: String) {
//    Log.d("teste", model)
//    val sharedPref = getSharedPref()
//  }

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