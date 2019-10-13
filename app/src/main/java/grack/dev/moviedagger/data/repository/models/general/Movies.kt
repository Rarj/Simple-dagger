package grack.dev.moviedagger.data.repository.models.general

data class Movies(
  val nowPlaying: List<Result>,
  val popular: List<Result>,
  val topRated: List<Result>,
  val upcoming: List<Result>
)