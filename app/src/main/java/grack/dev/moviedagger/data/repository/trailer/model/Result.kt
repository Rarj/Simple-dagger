package grack.dev.moviedagger.data.repository.trailer.model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("results")
    var results: List<ItemResponse>
)