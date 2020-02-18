package grack.dev.moviedagger.ui.movie.more

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.jakewharton.rxbinding3.view.clicks
import dagger.android.AndroidInjection
import grack.dev.moviedagger.AppConstant.DURATION_THROTTLE
import grack.dev.moviedagger.AppConstant.INTENT_KEY
import grack.dev.moviedagger.AppConstant.INTENT_KEY_HEADER
import grack.dev.moviedagger.R
import grack.dev.moviedagger.data.repository.models.general.Movies
import grack.dev.moviedagger.data.repository.models.general.Result
import grack.dev.moviedagger.databinding.ActivityMoreBinding
import grack.dev.moviedagger.ui.detail.DetailActivity
import grack.dev.moviedagger.ui.movie.search.SearchActivity
import grack.dev.moviedagger.utils.ClickListener
import grack.dev.moviedagger.utils.MarginItemDecoration
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MoreActivity : AppCompatActivity() {

  @Inject
  internal lateinit var viewModelFactory: ViewModelProvider.Factory

  private lateinit var viewModel: MoreViewModel
  private lateinit var binding: ActivityMoreBinding
  private lateinit var adapter: MoreAdapter

  @SuppressLint("CheckResult")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidInjection.inject(this)

    viewModel = ViewModelProviders.of(this, viewModelFactory).get(MoreViewModel::class.java)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_more)
    binding.moreViewModel = viewModel
    binding.lifecycleOwner = this

    viewModel.movies = Gson().fromJson(intent.getStringExtra(INTENT_KEY), Movies::class.java)
    viewModel.header = intent.getStringExtra(INTENT_KEY_HEADER)

    when {
      viewModel.header == resources.getString(R.string.movie_caption_now_playing) -> viewModel.result = viewModel.movies?.nowPlaying
      viewModel.header == resources.getString(R.string.movie_caption_popular) -> viewModel.result = viewModel.movies?.popular
      viewModel.header == resources.getString(R.string.movie_caption_upcoming) -> viewModel.result = viewModel.movies?.upcoming
      viewModel.header == resources.getString(R.string.movie_caption_top_rated) -> viewModel.result = viewModel.movies?.topRated
    }

    adapter = MoreAdapter(this, viewModel.result, object : ClickListener<Result> {
      override fun onItemClick(t: Result?) {
        startActivityDetail(t)
      }
    })
    binding.recyclerMore.addItemDecoration(MarginItemDecoration(16))
    binding.recyclerMore.layoutManager = LinearLayoutManager(this)
    binding.recyclerMore.adapter = adapter

    binding.buttonSearchMore.clicks()
      .throttleFirst(DURATION_THROTTLE, TimeUnit.MILLISECONDS)
      .subscribe {
        startActivity(Intent(this, SearchActivity::class.java))
      }
  }

  private fun startActivityDetail(data: Result?) {
    val bundle = Bundle()
    bundle.putString(INTENT_KEY, Gson().toJson(data))
    val detailActivity = DetailActivity()
    detailActivity.arguments = bundle
    detailActivity.show(supportFragmentManager, detailActivity.tag)
  }

}
