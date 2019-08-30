package grack.dev.moviedagger.ui.nowplaying

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import grack.dev.moviedagger.api.nowplaying.NowPlayingProvider
import grack.dev.moviedagger.api.nowplaying.model.Result
import grack.dev.moviedagger.di.DaggerApiComponents
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NowPlayingViewModel : ViewModel() {

  @Inject
  lateinit var nowPlayingService: NowPlayingProvider

  private val disposable = CompositeDisposable()

  val nowPlayingList = MutableLiveData<List<Result>>()

  init {
    DaggerApiComponents.create().inject(this)
    loadNowPlaying()
  }

  private fun loadNowPlaying() {
    disposable.add(
      nowPlayingService.getCountries()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
          nowPlayingList.value = it.results
        })
  }

  override fun onCleared() {
    super.onCleared()
    disposable.clear()
  }

}