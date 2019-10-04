package grack.dev.moviedagger.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import grack.dev.moviedagger.AppController
import grack.dev.moviedagger.di.module.ActivityModule
import grack.dev.moviedagger.di.module.ApiModule
import grack.dev.moviedagger.di.module.FragmentModule
import grack.dev.moviedagger.di.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
  modules = [ApiModule::class,
    ViewModelModule::class, AndroidSupportInjectionModule::class,
    ActivityModule::class, FragmentModule::class]
)
interface ApiComponents {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    @BindsInstance
    fun apiModule(apiModule: ApiModule): Builder

    fun build(): ApiComponents
  }

  fun inject(appController: AppController)

}