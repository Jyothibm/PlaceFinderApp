package geekboy.placefinder.mvvm.search


import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import geekboy.placefinder.R
import geekboy.placefinder.databinding.ActivitySearchBinding
import geekboy.placefinder.mvvm.base.BaseActivity
import geekboy.placefinder.mvvm.recentsearch.RecentSearchFragment
import geekboy.placefinder.viewmodel.AppViewModelFactory
import io.nlopez.smartlocation.SmartLocation
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import javax.inject.Inject
import android.widget.Toast
import androidx.lifecycle.Observer
import geekboy.placefinder.mvvm.places.PlacesActivity
import geekboy.placefinder.mvvm.places.RENDER_BOTH_MAP_LIST
import permissions.dispatcher.OnPermissionDenied



@RuntimePermissions
class SearchActivity : BaseActivity<SearchViewModel>(), HasSupportFragmentInjector {

    private val FRAGMENT_TAG = "RECENT_SEARCH_FRAGMENT"

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory

    private lateinit var searchViewModel: SearchViewModel

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private val recentSearchFragment = RecentSearchFragment.newInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val searchBinding by lazy {
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_search
            ) as ActivitySearchBinding
        }
        searchBinding.searchVM = searchViewModel
       loadRecentSearchFragment()
       fetchCurrentLocationWithPermissionCheck()
       observePlacesResults()
    }

    private fun observePlacesResults() {
        searchViewModel.placeData.observe(this, Observer {
            recentSearchFragment.loadRecentSearch()
            if (it.code()==200){
                if (it.body()?.placesList?.size?:0 > 0){
                    val intent = Intent (SearchActivity@this, PlacesActivity::class.java)
                    intent.putExtra("place_list",it.body()?.placesList)
                    intent.putExtra("title",searchViewModel.searchString.get().toString())
                    intent.putExtra("type", RENDER_BOTH_MAP_LIST)
                    startActivity(intent)
                }else{
                    Toast.makeText(SearchActivity@this,"No Result Found", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    fun fetchCurrentLocation() {
        SmartLocation.with(this).location().oneFix().start {
            searchViewModel.currentLocation = "${it.latitude},${it.longitude}"
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode,grantResults)
    }

    @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION)
    fun showDeniedForLocation() {
        Toast.makeText(this, "Need Permission to search by current location", Toast.LENGTH_SHORT).show()
    }

    private fun loadRecentSearchFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.recentSearchLayout, recentSearchFragment, FRAGMENT_TAG)
            .commitAllowingStateLoss()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    override fun getViewModel(): SearchViewModel {
        searchViewModel = ViewModelProviders.of(this, appViewModelFactory).get(SearchViewModel::class.java)
        return searchViewModel
    }
}
