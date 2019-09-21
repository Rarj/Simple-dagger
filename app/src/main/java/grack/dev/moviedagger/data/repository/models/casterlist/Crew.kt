package grack.dev.moviedagger.data.repository.models.casterlist


import com.google.gson.annotations.SerializedName

data class Crew(
  @SerializedName("credit_id")
  var creditId: String?,
  @SerializedName("department")
  var department: String?,
  @SerializedName("gender")
  var gender: Int?,
  @SerializedName("id")
  var id: Int?,
  @SerializedName("job")
  var job: String?,
  @SerializedName("name")
  var name: String?,
  @SerializedName("profile_path")
  var profilePath: String?
)