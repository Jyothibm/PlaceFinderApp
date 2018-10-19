package geekboy.placefinder.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import geekboy.placefinder.mvvm.places.placelist.PlaceListingFragment
import geekboy.placefinder.mvvm.places.placemap.PlaceMapFragment

@Module
abstract class PlaceFragmentBindingModule {
    @ContributesAndroidInjector
    abstract fun bindPlaceMapFragment(): PlaceMapFragment

    @ContributesAndroidInjector
    abstract fun bindPlaceListFragment(): PlaceListingFragment
}