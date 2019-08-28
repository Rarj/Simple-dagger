package grack.dev.moviedagger.api.nowplaying

import grack.dev.moviedagger.api.nowplaying.model.Response
import grack.dev.moviedagger.api.nowplaying.model.Result
import io.reactivex.Single
import retrofit2.http.GET

interface NowPlayingService {

    @GET("popular?api_key=33ef4526082667e26fd77c6773694d55")
    fun getCountries(): Single<Response>

}