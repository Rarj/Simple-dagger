package grack.dev.moviedagger.ui.detail

import androidx.lifecycle.MutableLiveData
import grack.dev.moviedagger.base.BaseViewModel
import grack.dev.moviedagger.data.repository.MovieService
import grack.dev.moviedagger.data.repository.models.casterlist.ResponseCastList
import grack.dev.moviedagger.data.repository.models.general.Result
import grack.dev.moviedagger.data.repository.trailer.TrailerRepository
import javax.inject.Inject

class DetailViewModel @Inject constructor(
  movieService: MovieService
) : BaseViewModel() {

  var result = MutableLiveData<Result>()

  var repository = TrailerRepository(movieService)
  var itemTrailer = MutableLiveData<List<grack.dev.moviedagger.data.repository.models.trailer.Result>>()
  var itemCast = MutableLiveData<ResponseCastList>()

  fun loadTrailer(movie_id: Int?) {
    addToDisposable(
      repository.loadTrailer(movie_id)
        .subscribe { response ->
          itemTrailer.postValue(response.results)
        }
    )
  }

  fun loadCast(movie_id: Int?) {
    addToDisposable(
      repository.loadCast(movie_id)
        .subscribe { response ->
          itemCast.postValue(response)
        }
    )
  }

  override fun onCleared() {
    super.onCleared()
    clearDisposable()
  }

}