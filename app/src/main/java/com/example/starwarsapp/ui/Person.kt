package com.example.starwarsapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
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
import com.example.starwarsapp.data.CharacterWrapper
import com.example.starwarsapp.domain.Character
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CharacterActivity : AppCompatActivity() {

  lateinit var btnBackBottom: Button
  lateinit var list: ListView
  lateinit var btnBackbtnBackTop: FloatingActionButton
  lateinit var progress: ProgressBar
  lateinit var noInternetImg : ImageView
  lateinit var noInternetText : TextView
  lateinit var characterApi : CharacterApi
  public var index = com.example.starwarsapp.ui.SelectorActivity().index

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_person)
    setupRetrofit()
    setupView()
    setupListeners()
    setupCachedResult()


    if (checkForInternet()) {
       getAllCharacters()
     } else {
       emptyState()
     }

    val checkInternet = checkForInternet()
    Log.d("There is internet?:", checkInternet.toString())
  }

  fun setupCachedResult() {
    val sharedPref = getSharedPref()
    Toast.makeText(this, "Ultimo personagem selecionado: ${sharedPref}", Toast.LENGTH_LONG).show()
  }


  override fun onResume() {
    super.onResume()
    if (checkForInternet()) {
        getAllCharacters()
    } else {
      emptyState()
    }
    Log.d("RESUME", "RESUME")
  }

  fun setupRetrofit() {
    val retrofit = Retrofit.Builder()
      .baseUrl("https://swapi.dev/api/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
    characterApi = retrofit.create(CharacterApi::class.java)
  }

  
fun getAllCharacters() {
  characterApi.getAllCharacters().enqueue(object : Callback<CharacterWrapper> {
    override fun onFailure(p0: Call<CharacterWrapper>, p1: Throwable) {
      Toast.makeText(this@CharacterActivity, R.string.response_error, Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(p0: Call<CharacterWrapper>, p1: Response<CharacterWrapper>) {
      p1.body()?.let {setupList(it.results, index)}
      list.visibility = VISIBLE
      // Desabilita loader e aviso de conexão com a internet
        progress.visibility = GONE
        noInternetImg.visibility = GONE
        noInternetText.visibility = GONE
    }
  })
}

  fun emptyState() {
    list.visibility = GONE
    noInternetImg.visibility = VISIBLE
    noInternetText.visibility = VISIBLE
    progress.visibility = GONE
  }

  fun setupView() {
    btnBackBottom = findViewById(R.id.btnBackBottom)
    btnBackbtnBackTop = findViewById(R.id.btnBackTop)
    list = findViewById(R.id.lista)
    progress = findViewById(R.id.tbLoader)
    noInternetImg = findViewById(R.id.iv_empty_state)
    noInternetText = findViewById(R.id.tv_no_wifi)
  }

  fun setupList(list: List<Character>, index: Int) {
    val model = list[index]
    val modelToArrayList = arrayOf(
      "Nome: ${model.name}",
      "Altura: ${model.height}",
      "Cor do Cabelo: ${model.hair_color}",
      "Cor da Pele: ${model.skin_color}",
      "Cor dos Olhos: ${model.eye_color}",
      "Ano de Nascimento: ${model.birth_year}",
      "Gênero: ${model.gender}",
      "Planeta Natal: ${model.homeworld}",
      "Peso: ${model.mass}",
      "Filmes:${model.films}",
      "Especies: ${model.species}",
      "Veículos: ${model.vehicles}",
      "Naves Espaciais: ${model.starships}",
      "URL: ${model.url}"
    )

    val adapter = ArrayAdapter(this, R.layout.list_item, R.id.text_view, modelToArrayList)
    this.list.adapter = adapter
    progress.visibility = VISIBLE
    saveSharedPref(model.name)
  }

  @SuppressLint("SuspiciousIndentation")
  fun saveSharedPref(list: String) {
    val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
      with(sharedPref.edit()) {
        putString(getString(R.string.saved_character), list)
        apply()
      }
  }

  fun getSharedPref(): String {
    val sharedPref = getPreferences(Context.MODE_PRIVATE)
    return sharedPref.getString(getString(R.string.saved_character), "empty").toString()
  }

  fun setupListeners() {
    btnBackBottom.setOnClickListener {
      startActivity(Intent(this, SelectorActivity::class.java))
    }

    btnBackbtnBackTop.setOnClickListener {
      startActivity(Intent(this, SelectorActivity::class.java))
    }
 }

  fun checkForInternet(): Boolean {
    val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      val network = connectivityManager.activeNetwork ?: return false
      val activeNetwork = connectivityManager.getNetworkCapabilities((network)) ?: return false
      return when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
      }
    } else {
        @Suppress("DEPRECATION")
        val networkInfo = connectivityManager.activeNetworkInfo ?: return false
        @Suppress("DEPRECATION")
        return networkInfo.isConnected
      }
    }
}