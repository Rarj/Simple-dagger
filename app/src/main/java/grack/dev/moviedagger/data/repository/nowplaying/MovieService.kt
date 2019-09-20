package grack.dev.moviedagger.data.repository.nowplaying

import grack.dev.moviedagger.data.repository.nowplaying.model.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

  @GET("{type}?language=id-ID")
  fun fetchMoviesByType(
    @Path("type") type: String
  ): Observable<Response>

}