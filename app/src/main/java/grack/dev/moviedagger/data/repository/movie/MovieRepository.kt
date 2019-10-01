package grack.dev.moviedagger.data.repository.movie

import grack.dev.moviedagger.data.repository.MovieService
import grack.dev.moviedagger.data.repository.models.general.ResponseGeneral
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
class MovieRepository(
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