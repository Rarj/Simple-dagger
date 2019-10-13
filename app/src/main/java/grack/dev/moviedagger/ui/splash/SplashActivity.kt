package grack.dev.moviedagger.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import dagger.android.AndroidInjection
import grack.dev.moviedagger.AppConstant.INTENT_KEY
import grack.dev.moviedagger.R
import grack.dev.moviedagger.ui.movie.bottomnavigation.BottomNavigationActivity
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  private lateinit var viewModel: SplashViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    AndroidInjection.inject(this)

    setContentView(R.layout.activity_splash)

    viewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel::class.java)

    initData()
  }

  private fun initData() {
    viewModel.movies.observe(this, Observer { movies ->
      if (movies != null) {
        val intent = Intent(this, BottomNavigationActivity::class.java)
        intent.putExtra(INTENT_KEY, Gson().toJson(movies))
        startActivity(intent)
        finish()
      }
    })
  }

}
