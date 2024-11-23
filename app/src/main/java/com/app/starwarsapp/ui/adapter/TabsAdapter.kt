package com.app.starwarsapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.starwarsapp.ui.ContactFragment
import com.app.starwarsapp.ui.IndexFragment

class TabsAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

  override fun createFragment(position: Int): Fragment {
    return when(position) {
      0 -> IndexFragment()
      1 -> ContactFragment()
      else -> IndexFragment()
    }
  }

  override fun getItemCount(): Int {
    return 2
  }
}