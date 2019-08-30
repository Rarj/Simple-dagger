package grack.dev.moviedagger.di

import dagger.Module
import dagger.Provides
import grack.dev.moviedagger.api.nowplaying.NowPlayingProvider
import grack.dev.moviedagger.api.nowplaying.NowPlayingService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

  private val BASE_URL = "http://api.themoviedb.org/3/movie/"

  @Provides
  fun provideCountriesApi(): NowPlayingService {
    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build()
      .create(NowPlayingService::class.java)
  }

  @Provides
  fun provideNowPlayingService(): NowPlayingProvider {
    return NowPlayingProvider()
  }
}