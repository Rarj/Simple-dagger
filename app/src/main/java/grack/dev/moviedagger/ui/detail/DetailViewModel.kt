package grack.dev.moviedagger.ui.detail

import androidx.lifecycle.MutableLiveData
import grack.dev.moviedagger.base.BaseViewModel
import grack.dev.moviedagger.data.repository.nowplaying.model.Result
import grack.dev.moviedagger.data.repository.trailer.TrailerRepository
import grack.dev.moviedagger.data.repository.trailer.TrailerService
import grack.dev.moviedagger.data.repository.trailer.model.ItemResponse
import javax.inject.Inject

class DetailViewModel @Inject constructor(
  trailerService: TrailerService
) : BaseViewModel() {

  var result = MutableLiveData<Result>()

  var repository = TrailerRepository(trailerService)
  var itemTrailer = MutableLiveData<List<ItemResponse>>()

  fun loadTrailer(movie_id: Int?) {
    addToDisposable(
      repository.loadTrailer(movie_id)
        .subscribe { response ->
          itemTrailer.postValue(response.results)
        }
    )
  }

  override fun onCleared() {
    super.onCleared()
    clearDisposable()
  }

}