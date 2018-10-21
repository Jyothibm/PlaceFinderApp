package geekboy.placefinder.mvvm.places.placelist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import geekboy.placefinder.R
import geekboy.placefinder.mvvm.base.BaseFragment
import geekboy.placefinder.mvvm.places.RENDER_BOTH_MAP_LIST
import geekboy.placefinder.repository.model.places.PlacesModel
import geekboy.placefinder.viewmodel.AppViewModelFactory
import kotlinx.android.synthetic.main.fragment_place_listing.*
import java.lang.Boolean
import javax.inject.Inject

private const val ARG_PARAM_PLACES = "paramNearbyPlaces"
private const val ARG_PARAM_MODE = "loading_mode"

/**
 * A simple [Fragment] subclass.
 * Use the [PlaceListingFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PlaceListingFragment : BaseFragment<PlaceListViewModel>(), OnFavoriteItemClick {

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory

    private lateinit var placeListViewModel: PlaceListViewModel


    private lateinit var paramPlaceList: ArrayList<PlacesModel>
    private var mode: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramPlaceList = it.getParcelableArrayList(ARG_PARAM_PLACES)
            mode = it.getInt(ARG_PARAM_MODE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_place_listing, container, false)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param paramResult List of nearby places.
         * @return A new instance of fragment PlaceListingFragment.
         */
        @JvmStatic
        fun newInstance(paramResult: ArrayList<PlacesModel> , mode: Int) =
            PlaceListingFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PARAM_PLACES, paramResult)
                    putInt(ARG_PARAM_MODE, mode)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        if (mode== RENDER_BOTH_MAP_LIST) {
            inflatePlaceList()
        }else {
            observePlaceList()
            placeListViewModel.getAllFavoritePlaces()
        }
    }

    private fun inflatePlaceList(){
        rvPlaceList.apply {

            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = PlaceListingAdapter(paramPlaceList, this@PlaceListingFragment)
        }
    }

    private fun observePlaceList() {
        placeListViewModel.placeList.observe(this, Observer {
            if (it!=null && it.size>0){
                tvErrorMsg.visibility = View.GONE
                paramPlaceList=it
                inflatePlaceList()
            }else{
                tvErrorMsg.visibility = View.VISIBLE
            }
        })
    }

    override fun getViewModel(): PlaceListViewModel {
        placeListViewModel = ViewModelProviders.of(this, appViewModelFactory).get(PlaceListViewModel::class.java)
        return placeListViewModel
    }


    override fun onFavoriteClick(position: Int, state: kotlin.Boolean) {
        if (state) {
            placeListViewModel.insertInDb(paramPlaceList[position])
        } else {
            placeListViewModel.deleteInDb(paramPlaceList[position])
        }
    }


}

interface OnFavoriteItemClick {
    fun onFavoriteClick(position: Int, state: kotlin.Boolean)
}
