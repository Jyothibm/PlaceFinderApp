package geekboy.placefinder.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import geekboy.placefinder.mvvm.recentsearch.RecentSearchFragment


@Module
abstract class SearchFragmentBindingModule {
    @ContributesAndroidInjector
    abstract fun recentSearchFragment(): RecentSearchFragment
}