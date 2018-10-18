package geekboy.placefinder.repository.local.db.favsearch

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoritePlaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favoritePlace: FavoritePlace)

    @Query("SELECT * FROM FavoritePlace")
    abstract fun loadFavoritePlaces(): LiveData<List<FavoritePlace>>

}