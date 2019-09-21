package grack.dev.moviedagger.ui.caster

import androidx.lifecycle.MutableLiveData
import grack.dev.moviedagger.base.BaseViewModel
import grack.dev.moviedagger.data.repository.MovieService
import grack.dev.moviedagger.data.repository.caster.CasterRepository
import grack.dev.moviedagger.data.repository.models.casterdetail.ResponseCasterDetail
import javax.inject.Inject

class CasterViewModel @Inject constructor(
  movieService: MovieService
) : BaseViewModel() {

  val result = MutableLiveData<ResponseCasterDetail>()
  private val repository = CasterRepository(movieService)

  fun loadCasterDetail(caster_id: Int) {
    addToDisposable(
      repository.loadCaster(caster_id)
        .subscribe({ casterDetail ->
          result.value = casterDetail
        }, {

        })
    )
  }

  override fun onCleared() {
    super.onCleared()
    clearDisposable()
  }

}