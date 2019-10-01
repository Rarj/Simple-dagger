package grack.dev.moviedagger.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import grack.dev.moviedagger.R
import grack.dev.moviedagger.ui.movie.MovieActivity

class SplashActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)

    startActivity(Intent(this, MovieActivity::class.java))
    finish()
  }
}
