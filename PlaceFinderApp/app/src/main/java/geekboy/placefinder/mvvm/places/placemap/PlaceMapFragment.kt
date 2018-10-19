package geekboy.placefinder.mvvm.places.placemap

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

import geekboy.placefinder.R
import geekboy.placefinder.mvvm.base.BaseFragment
import geekboy.placefinder.repository.model.places.PlacesModel
import geekboy.placefinder.viewmodel.AppViewModelFactory
import javax.inject.Inject

private const val ARG_PARAM_PLACES = "paramNearbyPlaces"

class PlaceMapFragment : BaseFragment<PlaceMapViewModel>(), OnMapReadyCallback {

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory

    private lateinit var placeMapViewModel: PlaceMapViewModel

    private var paramPlaceList: ArrayList<PlacesModel>? = null

    private var mapFragment: SupportMapFragment? = null

    private lateinit var map: GoogleMap

    override fun getViewModel(): PlaceMapViewModel {
        placeMapViewModel = ViewModelProviders.of(this, appViewModelFactory).get(PlaceMapViewModel::class.java)
        return placeMapViewModel
    }

    companion object {
        fun newInstance(paramResult: ArrayList<PlacesModel>) = PlaceMapFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList(ARG_PARAM_PLACES, paramResult)
            }
        }
    }

    private lateinit var viewModel: PlaceMapViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramPlaceList = it.getParcelableArrayList(ARG_PARAM_PLACES)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.place_map_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PlaceMapViewModel::class.java)
        // TODO: Use the ViewModel
        initGMaps()
    }

    fun initGMaps() {
        mapFragment = childFragmentManager.findFragmentById(R.id.mapPlaces) as SupportMapFragment
        mapFragment?.getMapAsync(this@PlaceMapFragment)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.let { map = it }
        map.mapType = GoogleMap.MAP_TYPE_TERRAIN
        loadMarkers()
    }

    private fun loadMarkers() {
        paramPlaceList?.map {
            val title = it.placeName
            val address = it.addressModel.addressName
            val loc = it.geoLocation.location
            val latLong = LatLng(loc.latitude,loc.longitude)
            val markerOptions = MarkerOptions()
                .position(latLong)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .title(title)
                .snippet(address)
            if (map != null) {
                map.addMarker(markerOptions)
                val zoom = 19f
                val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLong, zoom)
                map.animateCamera(cameraUpdate)
            }
        }
    }


}
