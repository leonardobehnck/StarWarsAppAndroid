package com.example.starwarsapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Spinner
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

class SelectorActivity : AppCompatActivity() {
  lateinit var btnBackBottom: Button
  lateinit var btnBackbtnBackTop: FloatingActionButton
  lateinit var btnSelected: Button
  lateinit var progress: ProgressBar
  lateinit var spinner: Spinner
  lateinit var noInternetImg: ImageView
  lateinit var noInternetText: TextView
  lateinit var characterApi: CharacterApi
  private var index: Int = 0


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_selector)
    setupRetrofit()
    setupView()
    setupListeners()

    if (checkForInternet()) {
      getAllCharacters()
    } else {
      emptyState()
    }

    val checkInternet = checkForInternet()
    Log.d("There is internet?:", checkInternet.toString())
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
        Toast.makeText(this@SelectorActivity, R.string.response_error, Toast.LENGTH_SHORT).show()
      }

      override fun onResponse(p0: Call<CharacterWrapper>, p1: Response<CharacterWrapper>) {
        p1.body()?.let { setupList(it.results) }
        // Desabilita loader e aviso de conexão com a internet
        progress.visibility = GONE
        noInternetImg.visibility = View.GONE
        noInternetText.visibility = View.GONE
      }
    })
  }

  fun emptyState() {
    noInternetImg.visibility = View.VISIBLE
    noInternetText.visibility = View.VISIBLE
    progress.visibility = View.GONE
  }

  fun setupView() {
    btnBackBottom = findViewById(R.id.btnBackBottom)
    btnBackbtnBackTop = findViewById(R.id.btnBackTop)
    progress = findViewById(R.id.tbLoader)
    noInternetImg = findViewById(R.id.iv_empty_state)
    noInternetText = findViewById(R.id.tv_no_wifi)
    spinner = findViewById(R.id.spinnerCharacters)
    btnSelected = findViewById(R.id.btnSelected)
  }

  fun setupList(list: List<Character>) {
    val names = list.map { it.name }
    val adapter = ArrayAdapter(this, R.layout.list_item, R.id.text_view, names)
    spinner.adapter = adapter
    progress.visibility = VISIBLE
  }

  @SuppressLint("ClickableViewAccessibility")
  fun setupListeners() {
    btnBackBottom.setOnClickListener {
      startActivity(Intent(this, MainActivity::class.java))
    }

    btnBackbtnBackTop.setOnClickListener {
      startActivity(Intent(this, MainActivity::class.java))
    }

    btnSelected.setOnClickListener {
      val intent = Intent(this, CharacterActivity::class.java)
      intent.putExtra("index", index)
      startActivity(intent)
    }

    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
      }

      override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        index = position
      }
    }
  }

  fun checkForInternet(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

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

