package grack.dev.moviedagger.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import grack.dev.moviedagger.di.factory.ViewModelFactory
import grack.dev.moviedagger.ui.nowplaying.NowPlayingViewModel

@Module
internal abstract class ViewModelModule {

  @Binds
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelKey(NowPlayingViewModel::class)
  protected abstract fun nowPlayingViewModel(nowPlayingViewModel: NowPlayingViewModel): ViewModel

}