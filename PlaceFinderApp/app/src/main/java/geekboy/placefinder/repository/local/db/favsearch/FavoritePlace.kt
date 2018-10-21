package geekboy.placefinder.repository.local.db.favsearch

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.google.gson.annotations.SerializedName
import geekboy.placefinder.repository.local.db.recentsearch.RecentSearch

@Entity(primaryKeys = ["id"])
data class FavoritePlace(
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "rating")
    val rating: String,
    @ColumnInfo(name = "open_status")
    val openStatus: Boolean
)
