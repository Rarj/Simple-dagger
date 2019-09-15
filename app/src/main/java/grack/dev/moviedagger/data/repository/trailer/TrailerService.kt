package grack.dev.moviedagger.data.repository.trailer

import grack.dev.moviedagger.data.repository.trailer.model.Result
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface TrailerService {

  @GET("{movie_id}/videos?language=en-US")
  fun loadTrailer(
    @Path("movie_id") movie_id: Int?
  ): Observable<Result>


}