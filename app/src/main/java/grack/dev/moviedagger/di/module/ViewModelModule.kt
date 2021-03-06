package grack.dev.moviedagger.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import grack.dev.moviedagger.di.factory.ViewModelFactory
import grack.dev.moviedagger.ui.caster.CasterViewModel
import grack.dev.moviedagger.ui.detail.DetailViewModel
import grack.dev.moviedagger.ui.movie.catalogue.CatalogueViewModel
import grack.dev.moviedagger.ui.movie.more.MoreViewModel
import grack.dev.moviedagger.ui.movie.search.SearchViewModel
import grack.dev.moviedagger.ui.splash.SplashViewModel

@Module
internal abstract class ViewModelModule {

  @Binds
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelKey(DetailViewModel::class)
  protected abstract fun detailViewModel(detailViewModel: DetailViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(CasterViewModel::class)
  protected abstract fun casterViewModel(casterViewModel: CasterViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(CatalogueViewModel::class)
  protected abstract fun catalogueViewModel(catalogueViewModel: CatalogueViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(SplashViewModel::class)
  protected abstract fun splashViewModel(splashViewModel: SplashViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(MoreViewModel::class)
  protected abstract fun moreViewModel(moreViewModel: MoreViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(SearchViewModel::class)
  protected abstract fun searchViewModel(searchViewModel: SearchViewModel): ViewModel

}