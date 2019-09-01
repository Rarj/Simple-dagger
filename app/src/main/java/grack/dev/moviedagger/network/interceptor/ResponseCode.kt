package grack.dev.moviedagger.network.interceptor

object ResponseCode {

  const val RESPONSE_SUCCESS = 200
  const val RESPONSE_BAD_REQUEST = 400
  const val RESPONSE_UNAUTHORIZED = 401
  const val RESPONSE_FORBIDDEN = 403
  const val RESPONSE_NOT_FOUND = 404
  const val RESPONSE_REQUEST_TIMEOUT = 408
  const val RESPONSE_INTERNAL_SERVER_ERROR = 500
  const val RESPONSE_BAD_GATEWAY = 502
  const val RESPONSE_SERVICE_UNAVAILABLE = 503
  const val RESPONSE_GATEWAY_TIMEOUT = 504

}