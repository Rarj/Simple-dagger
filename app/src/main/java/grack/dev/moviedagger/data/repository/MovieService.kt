package grack.dev.moviedagger.data.repository

import grack.dev.moviedagger.data.repository.models.casterdetail.ResponseCasterDetail
import grack.dev.moviedagger.data.repository.models.casterlist.ResponseCastList
import grack.dev.moviedagger.data.repository.models.general.ResponseGeneral
import grack.dev.moviedagger.data.repository.models.trailer.ResponseTrailer
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

  @GET("movie/{type}?language=id-ID")
  fun loadMoviesByType(
    @Path("type") type: String
  ): Observable<ResponseGeneral>

  @GET("movie/{movie_id}/videos")
  fun loadTrailerList(
    @Path("movie_id") movie_id: Int?
  ): Observable<ResponseTrailer>

  @GET("movie/{movie_id}/credits")
  fun loadCasterList(
    @Path("movie_id") movie_id: Int?
  ): Observable<ResponseCastList>

  @GET("person/{caster_id}")
  fun loadCasterDetail(
    @Path("caster_id") caster_id: Int?
  ): Observable<ResponseCasterDetail>

}