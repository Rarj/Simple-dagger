package grack.dev.moviedagger.ui.movie.catalogue

import androidx.lifecycle.MutableLiveData
import grack.dev.moviedagger.base.BaseViewModel
import grack.dev.moviedagger.data.repository.MovieService
import grack.dev.moviedagger.data.repository.models.general.Result
import grack.dev.moviedagger.data.repository.movie.MovieRepository
import javax.inject.Inject

class CatalogueViewModel @Inject constructor(movieApiService: MovieService) : BaseViewModel() {

  private val repository = MovieRepository(movieApiService)
  private val movie = MutableLiveData<List<Result>>()

  fun loadMovies(movieType: String) {
    addToDisposable(
      repository.loadMoviesByType(movieType)
        .subscribe({ list ->
          getMoviesLiveData().postValue(list.results)
        }, {

        })
    )
  }

  fun getMoviesLiveData() = movie

  override fun onCleared() {
    super.onCleared()
    clearDisposable()
  }


}