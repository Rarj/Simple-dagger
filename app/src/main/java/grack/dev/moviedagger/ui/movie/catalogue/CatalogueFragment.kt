package grack.dev.moviedagger.ui.movie.catalogue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import dagger.android.support.AndroidSupportInjection
import grack.dev.moviedagger.AppConstant
import grack.dev.moviedagger.R
import grack.dev.moviedagger.data.repository.models.general.Result
import grack.dev.moviedagger.databinding.FragmentCatalogueBinding
import grack.dev.moviedagger.ui.detail.DetailActivity
import grack.dev.moviedagger.utils.ClickListener
import javax.inject.Inject

class CatalogueFragment : Fragment() {

  @Inject
  internal lateinit var viewModelFactory: ViewModelProvider.Factory

  lateinit var binding: FragmentCatalogueBinding
  private lateinit var catalogueAdapter: CatalogueAdapter
  lateinit var viewModel: CatalogueViewModel

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

    catalogueAdapter = CatalogueAdapter(
      arrayListOf(),
      object : ClickListener<Result> {
        override fun onItemClick(t: Result) {
          passData(t)
        }
      })

    binding.recyclerNowPlaying.layoutManager = GridLayoutManager(context, 2)
    binding.recyclerNowPlaying.adapter = catalogueAdapter

    viewModel.loadMovies(AppConstant.NOW_PLAYING)

    viewModel.getMoviesLiveData().observe(this, Observer { result ->
      hideStateLoading(result)
      catalogueAdapter.updateCountries(result)
      catalogueAdapter.notifyDataSetChanged()
    })

    binding.spinnerSelectType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onNothingSelected(parent: AdapterView<*>?) {

      }

      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedItem = parent?.getItemAtPosition(position)

        when {
          selectedItem.toString() == "Now Playing" -> {
            refreshPage(AppConstant.NOW_PLAYING)
          }
          selectedItem.toString() == "Popular" -> {
            refreshPage(AppConstant.POPULAR)
          }
          selectedItem.toString() == "Top Rated" -> {
            refreshPage(AppConstant.TOP_RATED)
          }
          selectedItem.toString() == "Upcoming" -> {
            refreshPage(AppConstant.UPCOMING)
          }
        }
      }
    }

    return binding.root
  }

  private fun passData(data: Result) {
    val bundle = Bundle()
    bundle.putString(AppConstant.INTENT_KEY, Gson().toJson(data))
    val detailActivity = DetailActivity()
    detailActivity.arguments = bundle
    detailActivity.show(activity!!.supportFragmentManager, detailActivity.tag)
  }

  private fun hideStateLoading(result: List<Result>) {
    if (result.isNotEmpty()) {
      binding.loadingAnimation.visibility = View.GONE
      binding.textCaptionLoading.visibility = View.GONE
      binding.recyclerNowPlaying.visibility = View.VISIBLE
    } else {
      binding.loadingAnimation.visibility = View.VISIBLE
      binding.textCaptionLoading.visibility = View.VISIBLE
      binding.recyclerNowPlaying.visibility = View.GONE
    }
  }

  private fun refreshPage(movieType: String) {
    viewModel.loadMovies(movieType)
    binding.loadingAnimation.visibility = View.VISIBLE
    binding.textCaptionLoading.visibility = View.VISIBLE
    binding.recyclerNowPlaying.visibility = View.GONE
  }

}
