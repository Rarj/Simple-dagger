package grack.dev.moviedagger.ui.nowplaying

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import dagger.android.AndroidInjection
import grack.dev.moviedagger.AppConstant.INTENT_KEY
import grack.dev.moviedagger.R
import grack.dev.moviedagger.data.repository.models.general.Result
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

    nowPlayingAdapter = NowPlayingAdapter(
      arrayListOf(),
      object : ClickListener<Result> {
        override fun onItemClick(t: Result) {
          passData(t)
        }
      })

    binding.recyclerNowPlaying.layoutManager = GridLayoutManager(this, 2)
    binding.recyclerNowPlaying.adapter = nowPlayingAdapter

    viewModel.getMoviesLiveData().observe(this, Observer {
      hideStateLoading(it)
      nowPlayingAdapter.updateCountries(it)
      nowPlayingAdapter.notifyDataSetChanged()
    })
  }

  private fun passData(data: Result) {
    val bundle = Bundle()
    bundle.putString(INTENT_KEY, Gson().toJson(data))
    val detailActivity = DetailActivity()
    detailActivity.arguments = bundle
    detailActivity.show(supportFragmentManager, detailActivity.tag)
  }

  private fun hideStateLoading(result: List<Result>) {
    if (result.isNotEmpty()) {
      binding.loadingAnimation.visibility = GONE
      binding.textCaptionLoading.visibility = GONE
      binding.recyclerNowPlaying.visibility = VISIBLE
    } else {
      binding.loadingAnimation.visibility = VISIBLE
      binding.textCaptionLoading.visibility = VISIBLE
      binding.recyclerNowPlaying.visibility = GONE
    }
  }

}
