package geekboy.placefinder.repository.model.places

import com.google.gson.annotations.SerializedName

data class PlacesResponse(@SerializedName("results")var placesList: ArrayList<PlacesModel>)



data class PlacesModel(@SerializedName("name")var placeName: String,
                       @SerializedName("plus_code")var addressModel: AddressModel)

data class AddressModel(@SerializedName("compound_code")var addressName: String)