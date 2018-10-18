package geekboy.placefinder.repository.local.db.recentsearch

import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["id"])
data class RecentSearch(
    val id: Int,
    @field:SerializedName("name")
    val name: String
)
