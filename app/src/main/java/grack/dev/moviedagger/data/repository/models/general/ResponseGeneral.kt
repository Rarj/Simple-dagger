package grack.dev.moviedagger.data.repository.models.general


import com.google.gson.annotations.SerializedName

data class ResponseGeneral(
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