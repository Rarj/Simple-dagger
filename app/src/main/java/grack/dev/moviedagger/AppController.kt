package grack.dev.moviedagger

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import grack.dev.moviedagger.di.component.DaggerApiComponents
import grack.dev.moviedagger.di.module.ApiModule
import javax.inject.Inject

class AppController : Application(), HasAndroidInjector {
  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

  override fun androidInjector(): AndroidInjector<Any> {
    return dispatchingAndroidInjector
  }

  override fun onCreate() {
    super.onCreate()
    DaggerApiComponents.builder()
      .application(this)
      .apiModule(ApiModule())
      .build().inject(this)
  }
}