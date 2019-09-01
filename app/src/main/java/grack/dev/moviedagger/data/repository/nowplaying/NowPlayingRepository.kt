package grack.dev.moviedagger.data.repository.nowplaying

import grack.dev.moviedagger.data.repository.nowplaying.model.Response
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
class NowPlayingRepository(
  private val movieApiService: MovieService
) {

  fun loadMoviesByType(type: String): Observable<Response> {
    return movieApiService.fetchMoviesByType(type)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .map { movieResponse ->
        movieResponse
      }
  }

}