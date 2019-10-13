package grack.dev.moviedagger.ui.movie.catalogue

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
import dagger.android.support.AndroidSupportInjection
import grack.dev.moviedagger.AppConstant
import grack.dev.moviedagger.R
import grack.dev.moviedagger.data.repository.models.general.Movies
import grack.dev.moviedagger.data.repository.models.general.Result
import grack.dev.moviedagger.databinding.FragmentCatalogueBinding
import grack.dev.moviedagger.ui.detail.DetailActivity
import grack.dev.moviedagger.ui.movie.catalogue.model.Child
import grack.dev.moviedagger.ui.movie.catalogue.model.SectionItem
import grack.dev.moviedagger.utils.ClickListener
import grack.dev.moviedagger.utils.MarginItemDecoration
import javax.inject.Inject

class CatalogueFragment(private var movies: Movies) : Fragment() {

  @Inject
  internal lateinit var viewModelFactory: ViewModelProvider.Factory

  private lateinit var binding: FragmentCatalogueBinding
  private lateinit var catalogueAdapter: CatalogueAdapter
  private lateinit var viewModel: CatalogueViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidSupportInjection.inject(this)
  }

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

    return binding.root
  }

  private fun populateMovie() {
    var child = ArrayList<Child>()
    val section = ArrayList<SectionItem>()

    movies.nowPlaying.forEachIndexed { index, result ->
      if (index <= 4) {
        child.add(Child(result))
      }
    }
    section.add(SectionItem("NOW PLAYING", child))

    child = ArrayList()
    movies.topRated.forEachIndexed { index, result ->
      if (index <= 4) {
        child.add(Child(result))
      }
    }
    section.add(SectionItem("TOP RATED", child))

    child = ArrayList()
    movies.popular.forEachIndexed { index, result ->
      if (index <= 4) {
        child.add(Child(result))
      }
    }
    section.add(SectionItem("POPULAR", child))

    child = ArrayList()
    movies.upcoming.forEachIndexed { index, result ->
      if (index <= 4) {
        child.add(Child(result))
      }
    }
    section.add(SectionItem("UPCOMING", child))

    catalogueAdapter = CatalogueAdapter(activity, section, object : ClickListener<Result> {
      override fun onItemClick(t: Result?) {
        passData(t)
      }
    })
    binding.recyclerCatalogue.adapter = catalogueAdapter
    binding.recyclerCatalogue.layoutManager = LinearLayoutManager(context)
    binding.recyclerCatalogue.addItemDecoration(MarginItemDecoration(16))

    catalogueAdapter.notifyDataSetChanged()
  }

  private fun passData(data: Result?) {
    val bundle = Bundle()
    bundle.putString(AppConstant.INTENT_KEY, Gson().toJson(data))
    val detailActivity = DetailActivity()
    detailActivity.arguments = bundle
    detailActivity.show(activity!!.supportFragmentManager, detailActivity.tag)
  }

}
