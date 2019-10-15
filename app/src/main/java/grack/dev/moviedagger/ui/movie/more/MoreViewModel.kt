package grack.dev.moviedagger.ui.movie.more

import grack.dev.moviedagger.base.BaseViewModel
import grack.dev.moviedagger.data.repository.models.general.Movies
import grack.dev.moviedagger.data.repository.models.general.Result
import javax.inject.Inject

class MoreViewModel @Inject constructor() : BaseViewModel() {

  var movies: Movies? = null
  var header: String? = ""
  var result: List<Result>? = null
}