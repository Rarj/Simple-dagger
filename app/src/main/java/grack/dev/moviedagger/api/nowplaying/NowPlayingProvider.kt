package grack.dev.moviedagger.api.nowplaying

import grack.dev.moviedagger.api.nowplaying.model.Response
import grack.dev.moviedagger.di.DaggerApiComponents
import io.reactivex.Observable
import javax.inject.Inject

class NowPlayingProvider {

  @Inject
  lateinit var api: NowPlayingService

  init {
    DaggerApiComponents.create().inject(this)
  }

  fun getCountries(): Observable<Response> {
    return api.getCountries()
  }

}