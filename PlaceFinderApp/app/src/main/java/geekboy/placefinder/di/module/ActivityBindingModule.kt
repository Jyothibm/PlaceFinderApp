package geekboy.placefinder.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import geekboy.placefinder.di.qualifiers.ActivityScoped
import geekboy.placefinder.mvvm.places.PlacesActivity
import geekboy.placefinder.mvvm.search.SearchActivity

/**
 * When SubComponent and its builder has no other methods then we can use ContributesAndroidInjector
 * to generate
 *
 * **/

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [(SearchFragmentBindingModule::class)])
    abstract fun searchActivity(): SearchActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun PlacesActivity(): PlacesActivity
}
