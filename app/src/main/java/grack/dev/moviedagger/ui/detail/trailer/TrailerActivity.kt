package grack.dev.moviedagger.ui.detail.trailer

import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import grack.dev.moviedagger.AppConstant.INTENT_KEY
import grack.dev.moviedagger.BuildConfig
import grack.dev.moviedagger.R
import kotlinx.android.synthetic.main.activity_youtube.*

class TrailerActivity : YouTubeBaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_youtube)

    val intent = intent.getStringExtra(INTENT_KEY)

    youtube_player.initialize(
      BuildConfig.API_KEY_YOUTUBE,
      object : YouTubePlayer.OnInitializedListener {
        override fun onInitializationSuccess(
          youtubePlayerProvider: YouTubePlayer.Provider?,
          youtubePlayer: YouTubePlayer?,
          b: Boolean
        ) {
          youtubePlayer?.cueVideo(intent)
        }

        override fun onInitializationFailure(
          youtubePlayerProvider: YouTubePlayer.Provider?,
          result: YouTubeInitializationResult?
        ) {

        }
      })
  }
}
