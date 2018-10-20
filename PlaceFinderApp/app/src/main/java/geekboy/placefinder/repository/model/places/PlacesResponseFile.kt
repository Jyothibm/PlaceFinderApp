package geekboy.placefinder.repository.model.places

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class PlacesResponse(@SerializedName("results") var placesList: ArrayList<PlacesModel>)


@Parcelize
data class PlacesModel(
    @SerializedName("name") var placeName: String,
    @SerializedName("geometry") var geoLocation: GeoAddressModel,
    @SerializedName("rating") var rating: Double = 0.0,
    @SerializedName("vicinity") var address: String
) : Parcelable

@Parcelize
data class GeoAddressModel(@SerializedName("location") var location: GeoLocation) : Parcelable

@Parcelize
data class GeoLocation(
    @SerializedName("lat") var latitude: Double,
    @SerializedName("lng") var longitude: Double
) : Parcelable

