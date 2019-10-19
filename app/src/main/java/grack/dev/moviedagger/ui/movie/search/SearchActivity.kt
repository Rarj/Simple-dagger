package grack.dev.moviedagger.ui.movie.search

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.jakewharton.rxbinding3.widget.afterTextChangeEvents
import dagger.android.AndroidInjection
import grack.dev.moviedagger.AppConstant
import grack.dev.moviedagger.AppConstant.DURATION_THROTTLE
import grack.dev.moviedagger.R
import grack.dev.moviedagger.data.repository.models.general.Result
import grack.dev.moviedagger.databinding.ActivitySearchBinding
import grack.dev.moviedagger.ui.detail.DetailActivity
import grack.dev.moviedagger.utils.ClickListener
import grack.dev.moviedagger.utils.MarginItemDecoration
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

  @Inject
  internal lateinit var viewModelFactory: ViewModelProvider.Factory

  private lateinit var binding: ActivitySearchBinding
  private lateinit var viewModel: SearchViewModel

  private lateinit var adapter: SearchMovieAdapter

  @SuppressLint("CheckResult")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    AndroidInjection.inject(this)

    viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
    binding.searchViewModel = viewModel
    binding.lifecycleOwner = this

    binding.inputSearch.afterTextChangeEvents()
      .skipInitialValue()
      .debounce(DURATION_THROTTLE, TimeUnit.MILLISECONDS)
      .subscribe {
        val query = it.editable.toString()

        if (query != "") {
          search(query)
        } else if (query == "") {
          search(" ")
        }
      }
  }

  @SuppressLint("CheckResult")
  private fun search(query: String) {
    viewModel.searchMovie(query)
      .subscribe {
        if (it.results.isNotEmpty()) {
          binding.imageStateSearch.visibility = INVISIBLE
          binding.textStateSearch.visibility = INVISIBLE

          adapter = SearchMovieAdapter(this, viewModel.listMovie.value, object : ClickListener<Result> {
            override fun onItemClick(t: Result?) {
              startActivityDetail(t)
            }
          })
          binding.recyclerSearch.visibility = VISIBLE
          binding.recyclerSearch.layoutManager = LinearLayoutManager(this)
          binding.recyclerSearch.addItemDecoration(MarginItemDecoration(16))
          binding.recyclerSearch.adapter = adapter
          adapter.notifyDataSetChanged()

        } else if (query.isBlank()) {
          binding.recyclerSearch.visibility = INVISIBLE
          binding.imageStateSearch.visibility = VISIBLE
          binding.textStateSearch.visibility = VISIBLE
          viewModel.captionEmptySearch.value = "Mau cari film apa?"
        } else {
          binding.recyclerSearch.visibility = INVISIBLE
          binding.imageStateSearch.visibility = VISIBLE
          binding.textStateSearch.visibility = VISIBLE
          viewModel.captionEmptySearch.value = "Film \"$query\" belum ada tuh."
        }
      }
  }

  private fun startActivityDetail(data: Result?) {
    val bundle = Bundle()
    bundle.putString(AppConstant.INTENT_KEY, Gson().toJson(data))
    val detailActivity = DetailActivity()
    detailActivity.arguments = bundle
    detailActivity.show(supportFragmentManager, detailActivity.tag)
  }

  override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
    if (currentFocus != null) {
      val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    return super.dispatchTouchEvent(ev)
  }
}
