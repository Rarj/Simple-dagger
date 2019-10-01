package grack.dev.moviedagger.ui.movie

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import dagger.android.AndroidInjection
import grack.dev.moviedagger.AppConstant.INTENT_KEY
import grack.dev.moviedagger.AppConstant.NOW_PLAYING
import grack.dev.moviedagger.AppConstant.POPULAR
import grack.dev.moviedagger.AppConstant.TOP_RATED
import grack.dev.moviedagger.AppConstant.UPCOMING
import grack.dev.moviedagger.R
import grack.dev.moviedagger.data.repository.models.general.Result
import grack.dev.moviedagger.databinding.ActivityMovieBinding
import grack.dev.moviedagger.ui.detail.DetailActivity
import grack.dev.moviedagger.utils.ClickListener
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {

  @Inject
  internal lateinit var viewModelFactory: ViewModelProvider.Factory

  lateinit var binding: ActivityMovieBinding
  private lateinit var nowPlayingAdapter: MovieAdapter
  lateinit var viewModel: MovieViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidInjection.inject(this)

    viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel::class.java)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
    binding.viewModel = viewModel
    binding.lifecycleOwner = this

    nowPlayingAdapter = MovieAdapter(
      arrayListOf(),
      object : ClickListener<Result> {
        override fun onItemClick(t: Result) {
          passData(t)
        }
      })

    binding.recyclerNowPlaying.layoutManager = GridLayoutManager(this, 2)
    binding.recyclerNowPlaying.adapter = nowPlayingAdapter

    viewModel.loadMovies(NOW_PLAYING)

    viewModel.getMoviesLiveData().observe(this, Observer { result ->
      hideStateLoading(result)
      nowPlayingAdapter.updateCountries(result)
      nowPlayingAdapter.notifyDataSetChanged()
    })

    binding.spinnerSelectType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onNothingSelected(parent: AdapterView<*>?) {

      }

      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedItem = parent?.getItemAtPosition(position)

        when {
          selectedItem.toString() == "Now Playing" -> {
            refreshPage(NOW_PLAYING)
          }
          selectedItem.toString() == "Popular" -> {
            refreshPage(POPULAR)
          }
          selectedItem.toString() == "Top Rated" -> {
            refreshPage(TOP_RATED)
          }
          selectedItem.toString() == "Upcoming" -> {
            refreshPage(UPCOMING)
          }
        }
      }
    }

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

  private fun refreshPage(movieType: String) {
    viewModel.loadMovies(movieType)
    binding.loadingAnimation.visibility = VISIBLE
    binding.textCaptionLoading.visibility = VISIBLE
    binding.recyclerNowPlaying.visibility = GONE
  }

}
