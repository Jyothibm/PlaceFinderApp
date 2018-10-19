package geekboy.placefinder.mvvm.places.placelist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import geekboy.placefinder.R
import geekboy.placefinder.repository.model.places.PlacesModel

private const val ARG_PARAM_PLACES = "paramNearbyPlaces"

/**
 * A simple [Fragment] subclass.
 * Use the [PlaceListingFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PlaceListingFragment : Fragment() {
    private var paramPlaceList: ArrayList<PlacesModel>? = null

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
        fun newInstance(paramResult: ArrayList<PlacesModel>) =
            PlaceListingFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PARAM_PLACES, paramResult)
                }
            }
    }
}
