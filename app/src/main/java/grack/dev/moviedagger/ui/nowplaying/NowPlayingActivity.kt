package grack.dev.moviedagger.ui.nowplaying

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import dagger.android.AndroidInjection
import grack.dev.moviedagger.R
import grack.dev.moviedagger.data.repository.nowplaying.model.Result
import grack.dev.moviedagger.databinding.ActivityNowPlayingBinding
import grack.dev.moviedagger.ui.detail.DetailActivity
import grack.dev.moviedagger.utils.ClickListener
import javax.inject.Inject

class NowPlayingActivity : AppCompatActivity() {

  @Inject
  internal lateinit var viewModelFactory: ViewModelProvider.Factory

  lateinit var binding: ActivityNowPlayingBinding
  private lateinit var nowPlayingAdapter: NowPlayingAdapter
  lateinit var viewModel: NowPlayingViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidInjection.inject(this)

    viewModel = ViewModelProviders.of(this, viewModelFactory).get(NowPlayingViewModel::class.java)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_now_playing)
    binding.viewModel = viewModel
    binding.lifecycleOwner = this

    nowPlayingAdapter = NowPlayingAdapter(arrayListOf(), object : ClickListener<Result> {
      override fun onItemClick(t: Result) {
        val bundle = Bundle()
        bundle.putString("detail", Gson().toJson(t))
        val detailActivity = DetailActivity()
        detailActivity.arguments = bundle
        detailActivity.show(supportFragmentManager, detailActivity.tag)
      }
    })

    binding.recyclerNowPlaying.layoutManager = GridLayoutManager(this, 2)
    binding.recyclerNowPlaying.adapter = nowPlayingAdapter

    viewModel.getMoviesLiveData().observe(this, Observer {
      nowPlayingAdapter.updateCountries(it)
      nowPlayingAdapter.notifyDataSetChanged()
    })
  }
}
