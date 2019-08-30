package grack.dev.moviedagger.di

import dagger.Component
import grack.dev.moviedagger.api.nowplaying.NowPlayingProvider
import grack.dev.moviedagger.ui.nowplaying.NowPlayingViewModel

@Component(modules = [ApiModule::class])
interface ApiComponents {

  fun inject(viewModel: NowPlayingViewModel)

  fun inject(service: NowPlayingProvider)

}