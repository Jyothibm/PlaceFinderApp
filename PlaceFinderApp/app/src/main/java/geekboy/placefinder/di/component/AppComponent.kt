package geekboy.weatherapp.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import geekboy.placefinder.PlaceFinderApplication
import geekboy.weatherapp.di.module.ActivityBindingModule
import geekboy.weatherapp.di.module.ApplicationModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (ApplicationModule::class),
    (ActivityBindingModule::class),
    (AndroidSupportInjectionModule::class)])
interface AppComponent: AndroidInjector<PlaceFinderApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: PlaceFinderApplication)

}