package grack.dev.moviedagger.ui.nowplaying

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import grack.dev.moviedagger.api.nowplaying.NowPlayingProvider
import grack.dev.moviedagger.api.nowplaying.model.Response
import grack.dev.moviedagger.api.nowplaying.model.Result
import grack.dev.moviedagger.di.DaggerApiComponents
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NowPlayingViewModel : ViewModel() {

    @Inject
    lateinit var nowPlayingService: NowPlayingProvider

    private val disposable = CompositeDisposable()

    lateinit var title: MutableLiveData<String>
    lateinit var description: MutableLiveData<String>

    val nowplayingList = MutableLiveData<List<Result>>()

    init {
        DaggerApiComponents.create().inject(this)
    }

    fun loadNowPlaying() {
        disposable.add(
            nowPlayingService.getCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Response>() {
                    override fun onSuccess(t: Response) {
                        nowplayingList.value = t.results
                    }

                    override fun onError(e: Throwable) {

                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}