package grack.dev.moviedagger.data.repository.trailer

import grack.dev.moviedagger.data.repository.trailer.model.cast.Response
import grack.dev.moviedagger.data.repository.trailer.model.trailer.Result
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface TrailerService {

  @GET("{movie_id}/videos")
  fun loadTrailer(
    @Path("movie_id") movie_id: Int?
  ): Observable<Result>

  @GET("{movie_id}/credits")
  fun loadCast(
    @Path("movie_id") movie_id: Int?
  ): Observable<Response>


}