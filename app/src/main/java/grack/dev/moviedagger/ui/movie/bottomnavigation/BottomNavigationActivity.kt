package grack.dev.moviedagger.ui.movie.bottomnavigation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import grack.dev.moviedagger.R
import grack.dev.moviedagger.ui.movie.catalogue.CatalogueFragment
import kotlinx.android.synthetic.main.activity_bottom_navigation.*

class BottomNavigationActivity : AppCompatActivity(),
  BottomNavigationView.OnNavigationItemSelectedListener {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_bottom_navigation)

    bottom_navigation_movie.itemIconTintList = null

    loadFragment(CatalogueFragment())

  }

  private fun loadFragment(fragment: Fragment?): Boolean {
    if (fragment != null) {
      supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
    return true
  }

  override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
    var fragment: Fragment? = null
    when (menuItem.itemId) {
      R.id.navigation_movie -> fragment = CatalogueFragment()
      R.id.navigation_favorite -> fragment = CatalogueFragment()
      R.id.navigation_history -> fragment = CatalogueFragment()
    }

    return loadFragment(fragment)
  }
}
