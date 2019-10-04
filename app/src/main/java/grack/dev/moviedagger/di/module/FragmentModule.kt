package grack.dev.moviedagger.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import grack.dev.moviedagger.ui.movie.catalogue.CatalogueFragment

@Module
abstract class FragmentModule {

  @ContributesAndroidInjector
  abstract fun contributeCatalogue(): CatalogueFragment

}