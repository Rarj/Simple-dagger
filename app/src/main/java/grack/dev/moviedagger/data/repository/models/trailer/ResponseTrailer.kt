package grack.dev.moviedagger.data.repository.models.trailer


import com.google.gson.annotations.SerializedName

data class ResponseTrailer(
  @SerializedName("id")
  var id: Int?,
  @SerializedName("results")
  var results: List<Result>
)