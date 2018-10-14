package geekboy.weatherapp.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import geekboy.placefinder.mvvm.places.PlacesViewModel
import geekboy.placefinder.mvvm.search.SearchViewModel
import geekboy.weatherapp.di.key.ViewModelKey

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlacesViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: PlacesViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}