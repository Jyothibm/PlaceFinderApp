package geekboy.placefinder.repository.local.db.recentsearch

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

import kotlinx.android.parcel.Parcelize

@Entity(indices = [Index(value = ["name"], unique = true)])
data class RecentSearch(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "name")
    val name: String
){
    constructor():this(null,"")
}
