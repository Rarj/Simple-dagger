package grack.dev.moviedagger.data.repository.models.casterlist


import com.google.gson.annotations.SerializedName

data class ResponseCastList(
  @SerializedName("cast")
  var cast: List<Cast>,
  @SerializedName("crew")
  var crew: List<Crew>,
  @SerializedName("id")
  var id: Int?
)