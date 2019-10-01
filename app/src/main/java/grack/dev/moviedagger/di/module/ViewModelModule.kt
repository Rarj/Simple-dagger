package grack.dev.moviedagger.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import grack.dev.moviedagger.di.factory.ViewModelFactory
import grack.dev.moviedagger.ui.caster.CasterViewModel
import grack.dev.moviedagger.ui.detail.DetailViewModel
import grack.dev.moviedagger.ui.movie.MovieViewModel

@Module
internal abstract class ViewModelModule {

  @Binds
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelKey(MovieViewModel::class)
  protected abstract fun movieViewModel(movieViewModel: MovieViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(DetailViewModel::class)
  protected abstract fun detailViewModel(detailViewModel: DetailViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(CasterViewModel::class)
  protected abstract fun casterViewModel(casterViewModel: CasterViewModel): ViewModel

}