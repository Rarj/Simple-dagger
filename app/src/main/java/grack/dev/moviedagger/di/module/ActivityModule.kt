package grack.dev.moviedagger.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import grack.dev.moviedagger.ui.caster.CasterActivity
import grack.dev.moviedagger.ui.detail.DetailActivity

@Module
abstract class ActivityModule {

  @ContributesAndroidInjector
  abstract fun contributeDetailActivity(): DetailActivity

  @ContributesAndroidInjector
  abstract fun contributeCasterActivity(): CasterActivity

}