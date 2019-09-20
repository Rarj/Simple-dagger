package grack.dev.moviedagger.data.repository.trailer

import grack.dev.moviedagger.data.repository.trailer.model.cast.Response
import grack.dev.moviedagger.data.repository.trailer.model.trailer.Result
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
class TrailerRepository(
  private val trailerService: TrailerService
) {

  fun loadTrailer(movie_id: Int?): Observable<Result> {
    return trailerService.loadTrailer(movie_id)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .map { response ->
        response
      }
  }

  fun loadCast(movie_id: Int?): Observable<Response> {
    return trailerService.loadCast(movie_id)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .map { response ->
        response
      }
  }

}