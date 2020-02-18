package grack.dev.moviedagger.ui.movie.catalogue

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.jakewharton.rxbinding3.view.clicks
import dagger.android.support.AndroidSupportInjection
import grack.dev.moviedagger.AppConstant
import grack.dev.moviedagger.AppConstant.DURATION_THROTTLE
import grack.dev.moviedagger.AppConstant.INTENT_KEY_HEADER
import grack.dev.moviedagger.R
import grack.dev.moviedagger.data.repository.models.general.Movies
import grack.dev.moviedagger.data.repository.models.general.Result
import grack.dev.moviedagger.databinding.FragmentCatalogueBinding
import grack.dev.moviedagger.ui.detail.DetailActivity
import grack.dev.moviedagger.ui.movie.catalogue.model.Child
import grack.dev.moviedagger.ui.movie.catalogue.model.SectionItem
import grack.dev.moviedagger.ui.movie.more.MoreActivity
import grack.dev.moviedagger.ui.movie.search.SearchActivity
import grack.dev.moviedagger.utils.ClickListener
import grack.dev.moviedagger.utils.MarginItemDecoration
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CatalogueFragment(private var movies: Movies) : Fragment() {

  @Inject
  internal lateinit var viewModelFactory: ViewModelProvider.Factory
  private lateinit var binding: FragmentCatalogueBinding
  private lateinit var catalogueAdapter: CatalogueAdapter
  private lateinit var viewModel: CatalogueViewModel

  private var child = ArrayList<Child>()
  private val section = ArrayList<SectionItem>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidSupportInjection.inject(this)
  }

  @SuppressLint("CheckResult")
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    viewModel = ViewModelProviders.of(this, viewModelFactory).get(CatalogueViewModel::class.java)
    binding = DataBindingUtil.inflate(
      inflater, R.layout.fragment_catalogue, container, false
    )
    binding.viewModel = viewModel
    binding.lifecycleOwner = this

    populateMovie()

    catalogueAdapter = CatalogueAdapter(activity, section, object : ClickListener<Result> {
      override fun onItemClick(t: Result?) {
        startActivityDetail(t)
      }
    }, object : ClickListener<String> {
      override fun onItemClick(t: String?) {
        startActivityMore(t, movies)
      }
    })
    binding.recyclerCatalogue.adapter = catalogueAdapter
    binding.recyclerCatalogue.layoutManager = LinearLayoutManager(context)
    binding.recyclerCatalogue.addItemDecoration(MarginItemDecoration(16))

    catalogueAdapter.notifyDataSetChanged()

    binding.buttonSearch.clicks()
      .throttleFirst(DURATION_THROTTLE, TimeUnit.MILLISECONDS)
      .subscribe {
        startActivity(Intent(activity, SearchActivity::class.java))
      }

    return binding.root
  }

  private fun populateMovie() {
    movies.nowPlaying.forEachIndexed { index, result ->
      if (index <= 4) {
        child.add(Child(result))
      }
    }
    section.add(SectionItem(resources.getString(R.string.movie_caption_now_playing), child))

    child = ArrayList()
    movies.topRated.forEachIndexed { index, result ->
      if (index <= 4) {
        child.add(Child(result))
      }
    }
    section.add(SectionItem(resources.getString(R.string.movie_caption_top_rated), child))

    child = ArrayList()
    movies.popular.forEachIndexed { index, result ->
      if (index <= 4) {
        child.add(Child(result))
      }
    }
    section.add(SectionItem(resources.getString(R.string.movie_caption_popular), child))

    child = ArrayList()
    movies.upcoming.forEachIndexed { index, result ->
      if (index <= 4) {
        child.add(Child(result))
      }
    }
    section.add(SectionItem(resources.getString(R.string.movie_caption_upcoming), child))
  }

  private fun startActivityMore(header: String?, movies: Movies) {
    val intent = Intent(activity, MoreActivity::class.java)
    intent.putExtra(AppConstant.INTENT_KEY, Gson().toJson(movies))
    intent.putExtra(INTENT_KEY_HEADER, header)
    startActivity(intent)
  }

  private fun startActivityDetail(data: Result?) {
    val bundle = Bundle()
    bundle.putString(AppConstant.INTENT_KEY, Gson().toJson(data))
    val detailActivity = DetailActivity()
    detailActivity.arguments = bundle
    detailActivity.isCancelable = false
    detailActivity.show(activity!!.supportFragmentManager, detailActivity.tag)
  }

}
