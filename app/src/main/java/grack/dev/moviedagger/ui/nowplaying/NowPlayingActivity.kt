package grack.dev.moviedagger.ui.nowplaying

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.AndroidInjection
import grack.dev.moviedagger.R
import grack.dev.moviedagger.databinding.ActivityNowPlayingBinding
import javax.inject.Inject

class NowPlayingActivity : AppCompatActivity() {

  @Inject
  internal lateinit var viewModelFactory: ViewModelProvider.Factory

  lateinit var binding: ActivityNowPlayingBinding
  private val nowPlayingAdapter = NowPlayingAdapter(arrayListOf())
  lateinit var viewModel: NowPlayingViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidInjection.inject(this)

    viewModel = ViewModelProviders.of(this, viewModelFactory).get(NowPlayingViewModel::class.java)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_now_playing)
    binding.viewModel = viewModel
    binding.lifecycleOwner = this

    binding.recyclerNowPlaying.layoutManager = GridLayoutManager(this, 2)
    binding.recyclerNowPlaying.adapter = nowPlayingAdapter

    viewModel.getMoviesLiveData().observe(this, Observer {
      nowPlayingAdapter.updateCountries(it)
      nowPlayingAdapter.notifyDataSetChanged()
    })
  }
}
