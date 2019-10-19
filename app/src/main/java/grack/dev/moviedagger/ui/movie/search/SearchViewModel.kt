package grack.dev.moviedagger.ui.movie.search

import androidx.lifecycle.MutableLiveData
import grack.dev.moviedagger.base.BaseViewModel
import grack.dev.moviedagger.data.repository.MovieService
import grack.dev.moviedagger.data.repository.models.general.ResponseGeneral
import grack.dev.moviedagger.data.repository.models.general.Result
import grack.dev.moviedagger.data.repository.search.SearchRepository
import io.reactivex.Observable
import javax.inject.Inject

class SearchViewModel @Inject constructor(val movieService: MovieService) : BaseViewModel() {

  private val repository = SearchRepository(movieService)
  var listMovie = MutableLiveData<List<Result>>()
  var captionEmptySearch = MutableLiveData<String>()

  init {
    listMovie.value = ArrayList()
    captionEmptySearch.value = "Mau cari film apa?"
  }

  fun searchMovie(query: String): Observable<ResponseGeneral> {
    return repository.searchMovie(query)
      .map { response ->
        listMovie.value = response.results

        response
      }

  }

}