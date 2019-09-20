package grack.dev.moviedagger.data.repository.trailer.model.cast


import com.google.gson.annotations.SerializedName

data class Response(
  @SerializedName("cast")
  var cast: List<Cast>,
  @SerializedName("crew")
  var crew: List<Crew>,
  @SerializedName("id")
  var id: Int?
)