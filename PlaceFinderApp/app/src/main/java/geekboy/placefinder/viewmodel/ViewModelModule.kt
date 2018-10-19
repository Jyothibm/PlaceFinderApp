package geekboy.placefinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import geekboy.placefinder.di.key.ViewModelKey
import geekboy.placefinder.mvvm.places.PlacesViewModel
import geekboy.placefinder.mvvm.places.placelist.PlaceListViewModel
import geekboy.placefinder.mvvm.places.placemap.PlaceMapViewModel
import geekboy.placefinder.mvvm.recentsearch.RecentSearchViewModel
import geekboy.placefinder.mvvm.search.SearchViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RecentSearchViewModel::class)
    abstract fun bindRecentSearchViewModel(recentSearchViewModel: RecentSearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlacesViewModel::class)
    abstract fun bindPlaceViewModel(placesViewModel: PlacesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlaceMapViewModel::class)
    abstract fun bindPlaceMapViewModel(placesMapViewModel: PlaceMapViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlaceListViewModel::class)
    abstract fun bindPlaceListViewModel(placesListViewModel: PlaceListViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}