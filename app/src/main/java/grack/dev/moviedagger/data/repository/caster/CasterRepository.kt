package grack.dev.moviedagger.data.repository.caster

import grack.dev.moviedagger.data.repository.MovieService
import grack.dev.moviedagger.data.repository.models.casterdetail.ResponseCasterDetail
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
class CasterRepository(
  private val movieService: MovieService
) {

  fun loadCaster(caster_id: Int): Observable<ResponseCasterDetail> {
    return movieService.loadCasterDetail(caster_id)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .map { movieResponse ->
        movieResponse
      }
  }

}