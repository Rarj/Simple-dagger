package grack.dev.moviedagger.network.interceptor

import grack.dev.moviedagger.BuildConfig.API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RequestInterceptor : Interceptor {

  @Throws(IOException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val originalUrl = originalRequest.url()
    val url = originalUrl.newBuilder()
      .addQueryParameter("api_key", API_KEY)
      .build()

    val requestBuilder = originalRequest.newBuilder().url(url)
    val request = requestBuilder.build()
    return chain.proceed(request)
  }

}