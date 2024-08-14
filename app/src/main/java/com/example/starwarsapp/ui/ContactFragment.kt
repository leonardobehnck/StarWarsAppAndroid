package com.example.starwarsapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.starwarsapp.R

class ContactFragment : Fragment(){
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.contact_fragment, container, false)
  }

  lateinit var cardEmail : CardView
  lateinit var cardGithub : CardView
  lateinit var cardLinkedin : CardView


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupListeners()
  }

  fun setupListeners(){
    //CardGitHub
    view?.apply {
      cardGithub = findViewById(R.id.cardGithub)
      cardGithub.setOnClickListener {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/leonardobehnck"))
        startActivity(intent)
      }

      //CardLinkedIn
      cardLinkedin = findViewById(R.id.cardLinkedIn)
      cardLinkedin.setOnClickListener {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://linkedin.com/in/leonardobehnck"))
        startActivity(intent)
      }

      //CardEmail
      cardEmail = findViewById(R.id.cardEmail)
      cardEmail.setOnClickListener{
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:leonardo.behnck@gmail.com"))
        startActivity(intent)
      }}
    }
  }
