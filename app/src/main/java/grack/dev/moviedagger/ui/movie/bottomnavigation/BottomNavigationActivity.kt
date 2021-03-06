package grack.dev.moviedagger.ui.movie.bottomnavigation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import grack.dev.moviedagger.AppConstant
import grack.dev.moviedagger.R
import grack.dev.moviedagger.data.repository.models.general.Movies
import grack.dev.moviedagger.databinding.ActivityBottomNavigationBinding
import grack.dev.moviedagger.ui.movie.catalogue.CatalogueFragment

class BottomNavigationActivity : AppCompatActivity(),
  BottomNavigationView.OnNavigationItemSelectedListener {

  private lateinit var movies: Movies
  private lateinit var binding: ActivityBottomNavigationBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_bottom_navigation)

    val intent = intent.getStringExtra(AppConstant.INTENT_KEY)
    movies = Gson().fromJson(intent, Movies::class.java)

    binding.bottomNavigationMovie.itemIconTintList = null

    loadFragment(CatalogueFragment(movies))
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
      R.id.navigation_movie -> fragment = CatalogueFragment(movies)
      R.id.navigation_favorite -> fragment = CatalogueFragment(movies)
      R.id.navigation_history -> fragment = CatalogueFragment(movies)
    }

    return loadFragment(fragment)
  }
}
