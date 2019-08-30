package grack.dev.moviedagger.api.nowplaying.model


import com.google.gson.annotations.SerializedName

data class Response(
  @SerializedName("dates")
  var dates: Dates?,
  @SerializedName("page")
  var page: Int?,
  @SerializedName("results")
  var results: List<Result>,
  @SerializedName("total_pages")
  var totalPages: Int?,
  @SerializedName("total_results")
  var totalResults: Int?
)