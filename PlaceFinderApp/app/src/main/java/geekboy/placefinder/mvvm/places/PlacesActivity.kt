package geekboy.placefinder.mvvm.places

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import geekboy.placefinder.R
import geekboy.placefinder.databinding.ActivityPlacesBinding
import geekboy.placefinder.mvvm.base.BaseActivity
import geekboy.placefinder.mvvm.places.placelist.PlaceListingFragment
import geekboy.placefinder.mvvm.places.placemap.PlaceMapFragment
import geekboy.placefinder.repository.model.places.PlacesModel
import geekboy.placefinder.viewmodel.AppViewModelFactory
import kotlinx.android.synthetic.main.activity_places.*
import javax.inject.Inject

const val RENDER_BOTH_MAP_LIST = 1
const val RENDER_ONLY_LIST = 2

class PlacesActivity : BaseActivity<PlacesViewModel>(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory

    lateinit var placesViewModel: PlacesViewModel

    lateinit var placeBinding: geekboy.placefinder.databinding.ActivityPlacesBinding


    private var stringTitle = ""
    private lateinit var listPlaces: ArrayList<PlacesModel>
    private var renderType = 0

    private lateinit var placeMapFragment: PlaceMapFragment
    private lateinit var placeListFragment: PlaceListingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        placeBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_places
        ) as ActivityPlacesBinding
        placeBinding.placeVM = placesViewModel

        intentData()
        initViews()
        ivToggleView.setOnClickListener {
            if (placesViewModel.isMapView.get() == true) {
                placesViewModel.isMapView.set(false)
                supportFragmentManager.beginTransaction().hide(placeMapFragment).commit()
                supportFragmentManager.beginTransaction().show(placeListFragment).commit()
            } else {
                placesViewModel.isMapView.set(true)
                supportFragmentManager.beginTransaction().show(placeMapFragment).commit()
                supportFragmentManager.beginTransaction().hide(placeListFragment).commit()
            }
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    private fun intentData() {
        intent?.let {
            stringTitle = it.getStringExtra("title")
            listPlaces = it.getParcelableArrayListExtra<PlacesModel>("place_list")
            renderType = it.getIntExtra("type", 0)
        }
    }

    private fun initViews() {
        when (renderType) {
            RENDER_BOTH_MAP_LIST -> {
                placeListFragment = PlaceListingFragment.newInstance(listPlaces, RENDER_BOTH_MAP_LIST)
                placeMapFragment = PlaceMapFragment.newInstance(listPlaces)
                supportFragmentManager.beginTransaction()
                    .add(R.id.togglePlaceView, placeMapFragment, "PlaceMapFragment").commitAllowingStateLoss()
                supportFragmentManager.beginTransaction()
                    .add(R.id.togglePlaceView, placeListFragment, "PlaceListFragment").commitAllowingStateLoss()
                supportFragmentManager.beginTransaction().hide(placeListFragment).commit()
            }
            RENDER_ONLY_LIST -> {
                supportFragmentManager.beginTransaction()
                    .add(R.id.togglePlaceView, placeListFragment, "PlaceListFragment").commitAllowingStateLoss()
            }
        }

        placesViewModel.searchTitle.set("$stringTitle near you")
    }


    override fun getViewModel(): PlacesViewModel {
        placesViewModel = ViewModelProviders.of(this, appViewModelFactory).get(PlacesViewModel::class.java)
        return placesViewModel
    }
}
