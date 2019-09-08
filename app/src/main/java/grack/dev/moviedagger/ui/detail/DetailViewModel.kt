package grack.dev.moviedagger.ui.detail

import androidx.lifecycle.MutableLiveData
import grack.dev.moviedagger.base.BaseViewModel
import grack.dev.moviedagger.data.repository.nowplaying.model.Result
import javax.inject.Inject

class DetailViewModel @Inject constructor() : BaseViewModel() {

  var result = MutableLiveData<Result>()

}