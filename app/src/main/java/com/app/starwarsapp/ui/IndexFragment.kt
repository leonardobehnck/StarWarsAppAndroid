package com.app.starwarsapp.ui


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.app.starwarsapp.R

class IndexFragment : Fragment(){
  lateinit var btnNext: Button
  lateinit var btnFavorite : Button

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.index_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupView(view)
    setupListeners()
  }

  fun setupView(view: View) {
    view.apply {
      btnNext = findViewById(R.id.btnNext)
      btnFavorite = findViewById(R.id.btnFavorite)
    }
  }

  fun setupListeners() {
    btnNext.setOnClickListener {
      startActivity(Intent(context, SelectorActivity::class.java))
    }

    btnFavorite.setOnClickListener {
      startActivity(Intent(context, FavoriteActivity::class.java))
    }
  }
}








