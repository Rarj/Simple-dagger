package grack.dev.moviedagger.data.repository.trailer

import grack.dev.moviedagger.data.repository.MovieService
import grack.dev.moviedagger.data.repository.models.casterlist.ResponseCastList
import grack.dev.moviedagger.data.repository.models.trailer.ResponseTrailer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
class TrailerRepository(
  private val movieServiceService: MovieService
) {

  fun loadTrailer(movie_id: Int?): Observable<ResponseTrailer> {
    return movieServiceService.loadTrailerList(movie_id)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .map { response ->
        response
      }
  }

  fun loadCast(movie_id: Int?): Observable<ResponseCastList> {
    return movieServiceService.loadCasterList(movie_id)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .map { response ->
        response
      }
  }

}