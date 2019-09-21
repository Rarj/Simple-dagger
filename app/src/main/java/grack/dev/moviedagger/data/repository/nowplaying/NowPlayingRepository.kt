package grack.dev.moviedagger.data.repository.nowplaying

import grack.dev.moviedagger.data.repository.MovieService
import grack.dev.moviedagger.data.repository.models.general.ResponseGeneral
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
class NowPlayingRepository(
  private val movieApiService: MovieService
) {

  fun loadMoviesByType(type: String): Observable<ResponseGeneral> {
    return movieApiService.loadMoviesByType(type)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .map { movieResponse ->
        movieResponse
      }
  }

}