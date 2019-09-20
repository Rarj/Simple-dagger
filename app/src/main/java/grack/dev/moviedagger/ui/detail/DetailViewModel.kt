package grack.dev.moviedagger.ui.detail

import androidx.lifecycle.MutableLiveData
import grack.dev.moviedagger.base.BaseViewModel
import grack.dev.moviedagger.data.repository.nowplaying.model.Result
import grack.dev.moviedagger.data.repository.trailer.TrailerRepository
import grack.dev.moviedagger.data.repository.trailer.TrailerService
import grack.dev.moviedagger.data.repository.trailer.model.cast.Response
import grack.dev.moviedagger.data.repository.trailer.model.trailer.ItemResponse
import javax.inject.Inject

class DetailViewModel @Inject constructor(
  trailerService: TrailerService
) : BaseViewModel() {

  var result = MutableLiveData<Result>()

  var repository = TrailerRepository(trailerService)
  var itemTrailer = MutableLiveData<List<ItemResponse>>()
  var itemCast = MutableLiveData<Response>()

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