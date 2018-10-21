package geekboy.placefinder.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import geekboy.placefinder.mvvm.places.placelist.PlaceListingFragment
import geekboy.placefinder.mvvm.places.placemap.PlaceMapFragment
import geekboy.placefinder.mvvm.recentsearch.RecentSearchFragment

@Module
abstract class FragmentBindingModule {
    @ContributesAndroidInjector
    abstract fun bindPlaceMapFragment(): PlaceMapFragment

    @ContributesAndroidInjector
    abstract fun bindPlaceListFragment(): PlaceListingFragment

    @ContributesAndroidInjector
    abstract fun recentSearchFragment(): RecentSearchFragment
}