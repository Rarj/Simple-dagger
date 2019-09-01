package grack.dev.moviedagger.ui.nowplaying

import androidx.lifecycle.MutableLiveData
import grack.dev.moviedagger.base.BaseViewModel
import grack.dev.moviedagger.data.repository.nowplaying.NowPlayingRepository
import grack.dev.moviedagger.data.repository.nowplaying.model.Result
import grack.dev.moviedagger.data.repository.nowplaying.MovieService
import javax.inject.Inject

class NowPlayingViewModel @Inject constructor(
  movieApiService: MovieService
) : BaseViewModel() {

  private val repository = NowPlayingRepository(movieApiService)
  private val movie = MutableLiveData<List<Result>>()

  init {
    loadMovies()
  }

  private fun loadMovies() {
    addToDisposable(
      repository.loadMoviesByType("now_playing")
        .subscribe {
          getMoviesLiveData().postValue(it.results)
        })
  }

  fun getMoviesLiveData() = movie

  override fun onCleared() {
    super.onCleared()
    clearDisposable()
  }

}