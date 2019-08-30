package grack.dev.moviedagger.ui.nowplaying

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import grack.dev.moviedagger.R
import grack.dev.moviedagger.databinding.ActivityNowPlayingBinding

class NowPlayingActivity : AppCompatActivity() {

  lateinit var viewModel: NowPlayingViewModel
  private val nowPlayingAdapter = NowPlayingAdapter(arrayListOf())
  lateinit var binding: ActivityNowPlayingBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_now_playing)
    viewModel = ViewModelProviders.of(this).get(NowPlayingViewModel::class.java)
    binding.viewModel = viewModel
    binding.lifecycleOwner = this

    binding.recyclerNowPlaying.layoutManager = GridLayoutManager(this, 2)
    binding.recyclerNowPlaying.adapter = nowPlayingAdapter

    viewModel.nowPlayingList.observe(this, Observer {
      nowPlayingAdapter.updateCountries(it)
      nowPlayingAdapter.notifyDataSetChanged()
    })

  }
}
