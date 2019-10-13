package grack.dev.moviedagger.ui.splash

import androidx.lifecycle.MutableLiveData
import grack.dev.moviedagger.AppConstant.NOW_PLAYING
import grack.dev.moviedagger.AppConstant.POPULAR
import grack.dev.moviedagger.AppConstant.TOP_RATED
import grack.dev.moviedagger.AppConstant.UPCOMING
import grack.dev.moviedagger.base.BaseViewModel
import grack.dev.moviedagger.data.repository.MovieService
import grack.dev.moviedagger.data.repository.models.general.Movies
import grack.dev.moviedagger.data.repository.models.general.ResponseGeneral
import grack.dev.moviedagger.data.repository.movie.MovieRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function4
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SplashViewModel @Inject constructor(movieService: MovieService) : BaseViewModel() {

  private val repository = MovieRepository(movieService)
  var movies = MutableLiveData<Movies>()

  init {
    loadAllMovies()
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe()
  }

  private fun fetchMovieByType(movieType: String): Observable<ResponseGeneral> {
    return repository.loadMoviesByType(movieType)
      .map { response ->
        response
      }
  }

  private fun loadAllMovies(): Observable<Movies> {
    return Observable.zip(
      fetchMovieByType(NOW_PLAYING).subscribeOn(Schedulers.io()),
      fetchMovieByType(POPULAR).subscribeOn(Schedulers.io()),
      fetchMovieByType(TOP_RATED).subscribeOn(Schedulers.io()),
      fetchMovieByType(UPCOMING).subscribeOn(Schedulers.io()),
      Function4<ResponseGeneral, ResponseGeneral, ResponseGeneral, ResponseGeneral, Movies> { nowPlaying, popular, topRated, upcoming ->
        movies.value = Movies(
          nowPlaying.results,
          popular.results,
          topRated.results,
          upcoming.results
        )

        movies.value!!
      }
    )
  }

  override fun onCleared() {
    super.onCleared()
    clearDisposable()
  }

}