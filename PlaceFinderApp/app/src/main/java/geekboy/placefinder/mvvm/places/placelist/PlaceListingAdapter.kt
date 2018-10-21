package geekboy.placefinder.mvvm.places.placelist

import android.service.autofill.Validators.not
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import geekboy.placefinder.BR
import geekboy.placefinder.repository.model.places.PlacesModel
import geekboy.placefinder.R
import kotlinx.android.synthetic.main.row_place_list.view.*

class PlaceListingAdapter(
    var listPlaces: ArrayList<PlacesModel>,
    val onFavoriteItemClick: OnFavoriteItemClick
) :
    RecyclerView.Adapter<PlaceListingAdapter.PlaceListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceListViewHolder {
        return PlaceListViewHolder(parent)
    }

    override fun getItemCount(): Int = listPlaces.size


    override fun onBindViewHolder(holder: PlaceListViewHolder, position: Int) {
        holder.bindItems(listPlaces[position])
        holder.getBindingObject().root.ivFavorite.setOnClickListener {
            val place = listPlaces[position]
            place.isFavorite = (place.isFavorite.not())
            listPlaces.set(position, place)
            notifyItemChanged(position)
            onFavoriteItemClick.onFavoriteClick(position, place.isFavorite)
        }
    }

    class PlaceListViewHolder(
        parent: ViewGroup,
        private val binding: geekboy.placefinder.databinding.RowPlaceListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_place_list,
            parent,
            false
        )
    ) : RecyclerView.ViewHolder(binding.root) {
        fun getBindingObject() = binding
        fun bindItems(item: PlacesModel) {
            binding.place = item
        }

    }
}