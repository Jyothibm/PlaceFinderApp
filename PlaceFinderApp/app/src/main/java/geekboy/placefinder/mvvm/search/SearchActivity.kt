package geekboy.placefinder.mvvm.search


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import geekboy.placefinder.R
import geekboy.placefinder.mvvm.base.BaseActivity
import geekboy.placefinder.mvvm.recentsearch.RecentSearchFragment
import geekboy.placefinder.viewmodel.AppViewModelFactory
import javax.inject.Inject

class SearchActivity : BaseActivity<SearchViewModel>(), HasSupportFragmentInjector {

    private val FRAGMENT_TAG = "RECENT_SEARCH_FRAGMENT"

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory

    private lateinit var searchViewModel: SearchViewModel

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        loadRecentSearchFragment()

    }

    private fun loadRecentSearchFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.recentSearchLayout, RecentSearchFragment.newInstance(), FRAGMENT_TAG)
            .commitAllowingStateLoss()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    override fun getViewModel(): SearchViewModel {
        searchViewModel = ViewModelProviders.of(this, appViewModelFactory).get(SearchViewModel::class.java)
        return searchViewModel
    }
}
