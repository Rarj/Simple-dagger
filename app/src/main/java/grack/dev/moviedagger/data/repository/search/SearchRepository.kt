package grack.dev.moviedagger.data.repository.search

import grack.dev.moviedagger.data.repository.MovieService
import grack.dev.moviedagger.data.repository.models.general.ResponseGeneral
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
class SearchRepository(private val movieService: MovieService) {

  fun searchMovie(query: String): Observable<ResponseGeneral> {
    return movieService.search(query)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .map { movieResponse ->
        movieResponse
      }
  }

}