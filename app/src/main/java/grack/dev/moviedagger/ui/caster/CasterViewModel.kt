package grack.dev.moviedagger.ui.caster

import androidx.lifecycle.MutableLiveData
import grack.dev.moviedagger.base.BaseViewModel
import grack.dev.moviedagger.data.repository.MovieService
import grack.dev.moviedagger.data.repository.caster.CasterRepository
import grack.dev.moviedagger.data.repository.models.casterdetail.ResponseCasterDetail
import io.reactivex.Observable
import javax.inject.Inject

class CasterViewModel @Inject constructor(
  movieService: MovieService
) : BaseViewModel() {

  val result = MutableLiveData<ResponseCasterDetail>()
  val loadingVisibility = MutableLiveData<Int>()
  val showDetail = MutableLiveData<Int>()
  private val repository = CasterRepository(movieService)

  init {
    loadingVisibility.value = 0
    showDetail.value = 8
  }

  fun loadCasterDetail(casterId: Int): Observable<Boolean> {
    return repository.loadCaster(casterId)
      .map {
        result.value = it

        true
      }
  }

  override fun onCleared() {
    super.onCleared()
    clearDisposable()
  }

}