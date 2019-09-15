package grack.dev.moviedagger.network.interceptor

import android.content.Context
import grack.dev.moviedagger.network.interceptor.ResponseCode.BAD_GATEWAY
import grack.dev.moviedagger.network.interceptor.ResponseCode.BAD_REQUEST
import grack.dev.moviedagger.network.interceptor.ResponseCode.FORBIDDEN
import grack.dev.moviedagger.network.interceptor.ResponseCode.GATEWAY_TIMEOUT
import grack.dev.moviedagger.network.interceptor.ResponseCode.INTERNAL_SERVER_ERROR
import grack.dev.moviedagger.network.interceptor.ResponseCode.NOT_FOUND
import grack.dev.moviedagger.network.interceptor.ResponseCode.REQUEST_TIMEOUT
import grack.dev.moviedagger.network.interceptor.ResponseCode.RESPONSE_BAD_GATEWAY
import grack.dev.moviedagger.network.interceptor.ResponseCode.RESPONSE_BAD_REQUEST
import grack.dev.moviedagger.network.interceptor.ResponseCode.RESPONSE_FORBIDDEN
import grack.dev.moviedagger.network.interceptor.ResponseCode.RESPONSE_GATEWAY_TIMEOUT
import grack.dev.moviedagger.network.interceptor.ResponseCode.RESPONSE_INTERNAL_SERVER_ERROR
import grack.dev.moviedagger.network.interceptor.ResponseCode.RESPONSE_NOT_FOUND
import grack.dev.moviedagger.network.interceptor.ResponseCode.RESPONSE_REQUEST_TIMEOUT
import grack.dev.moviedagger.network.interceptor.ResponseCode.RESPONSE_SERVICE_UNAVAILABLE
import grack.dev.moviedagger.network.interceptor.ResponseCode.RESPONSE_SUCCESS
import grack.dev.moviedagger.network.interceptor.ResponseCode.RESPONSE_UNAUTHORIZED
import grack.dev.moviedagger.network.interceptor.ResponseCode.SERVICE_UNAVAILABLE
import grack.dev.moviedagger.network.interceptor.ResponseCode.SUCCESS
import grack.dev.moviedagger.network.interceptor.ResponseCode.UNAUTHORIZED
import grack.dev.moviedagger.utils.Common.log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkInterceptor(private val ctx: Context) : Interceptor {

  @Throws(IOException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request()
    val response = chain.proceed(request)

    when {
      response.code() == RESPONSE_SUCCESS -> log(SUCCESS)
      response.code() == RESPONSE_BAD_REQUEST -> log(BAD_REQUEST)
      response.code() == RESPONSE_UNAUTHORIZED -> log(UNAUTHORIZED)
      response.code() == RESPONSE_FORBIDDEN -> log(FORBIDDEN)
      response.code() == RESPONSE_NOT_FOUND -> log(NOT_FOUND)
      response.code() == RESPONSE_REQUEST_TIMEOUT -> log(REQUEST_TIMEOUT)
      response.code() == RESPONSE_INTERNAL_SERVER_ERROR -> log(INTERNAL_SERVER_ERROR)
      response.code() == RESPONSE_BAD_GATEWAY -> log(BAD_GATEWAY)
      response.code() == RESPONSE_SERVICE_UNAVAILABLE -> log(SERVICE_UNAVAILABLE)
      response.code() == RESPONSE_GATEWAY_TIMEOUT -> log(GATEWAY_TIMEOUT)
    }

    if (ConnectivityStatus.isConnected(ctx)) {
      request.newBuilder()
        .header("Cache-Control", "public, max-age=" + 60)
        .build()

    } else {
      request.newBuilder()
        .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
    }

    return chain.proceed(request)
  }

}