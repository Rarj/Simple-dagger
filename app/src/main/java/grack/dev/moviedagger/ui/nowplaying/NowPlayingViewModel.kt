package grack.dev.moviedagger.ui.nowplaying

import android.util.Log
import androidx.lifecycle.MutableLiveData
import grack.dev.moviedagger.AppConstant.NOW_PLAYING
import grack.dev.moviedagger.base.BaseViewModel
import grack.dev.moviedagger.data.repository.nowplaying.MovieService
import grack.dev.moviedagger.data.repository.nowplaying.NowPlayingRepository
import grack.dev.moviedagger.data.repository.nowplaying.model.Result
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
      repository.loadMoviesByType(NOW_PLAYING)
        .subscribe({ list ->
          getMoviesLiveData().postValue(list.results)
        }, {
          Log.e("KKKKKKKKKKKKKKK", it.message)
        })
    )
  }

  fun getMoviesLiveData() = movie

  override fun onCleared() {
    super.onCleared()
    clearDisposable()
  }

}