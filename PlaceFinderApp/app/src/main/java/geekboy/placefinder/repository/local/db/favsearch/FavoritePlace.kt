package geekboy.placefinder.repository.local.db.favsearch

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(
    primaryKeys = ["id"]
)
data class FavoritePlace(val id: String,
                         @field:SerializedName("name")
                         val name: String,
                         @field:SerializedName("address")
                         val address: String,
                         @field:SerializedName("lat_lng")
                         val geo_address: String,
                         @field:SerializedName("rating")
                         val rating: String
)
