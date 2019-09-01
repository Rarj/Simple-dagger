package grack.dev.moviedagger.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import grack.dev.moviedagger.ui.nowplaying.NowPlayingActivity

@Module
abstract class ActivityModule {

  @ContributesAndroidInjector
  abstract fun contributeNowPlayingActivity(): NowPlayingActivity

}