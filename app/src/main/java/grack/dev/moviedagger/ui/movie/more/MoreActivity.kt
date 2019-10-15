package grack.dev.moviedagger.ui.movie.more

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import dagger.android.AndroidInjection
import grack.dev.moviedagger.AppConstant.INTENT_KEY
import grack.dev.moviedagger.R
import grack.dev.moviedagger.data.repository.models.general.Movies
import grack.dev.moviedagger.databinding.ActivityMoreBinding
import grack.dev.moviedagger.utils.MarginItemDecoration
import javax.inject.Inject

class MoreActivity : AppCompatActivity() {

  @Inject
  internal lateinit var viewModelFactory: ViewModelProvider.Factory

  private lateinit var viewModel: MoreViewModel
  private lateinit var binding: ActivityMoreBinding
  private lateinit var adapter: MoreAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidInjection.inject(this)

    viewModel = ViewModelProviders.of(this, viewModelFactory).get(MoreViewModel::class.java)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_more)
    binding.moreViewModel = viewModel
    binding.lifecycleOwner = this

    viewModel.movies = Gson().fromJson(intent.getStringExtra(INTENT_KEY), Movies::class.java)
    viewModel.header = intent.getStringExtra("header")

    when {
      viewModel.header == "NOW PLAYING" -> viewModel.result = viewModel.movies?.nowPlaying
      viewModel.header == "POPULAR" -> viewModel.result = viewModel.movies?.popular
      viewModel.header == "UPCOMING" -> viewModel.result = viewModel.movies?.upcoming
      viewModel.header == "TOP RATED" -> viewModel.result = viewModel.movies?.topRated
    }

    adapter = MoreAdapter(this, viewModel.result)
    binding.recyclerMore.addItemDecoration(MarginItemDecoration(16))
    binding.recyclerMore.layoutManager = LinearLayoutManager(this)
    binding.recyclerMore.adapter = adapter

  }
}
