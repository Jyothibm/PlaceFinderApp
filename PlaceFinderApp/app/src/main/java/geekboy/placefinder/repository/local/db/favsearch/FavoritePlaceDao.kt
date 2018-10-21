package geekboy.placefinder.repository.local.db.favsearch

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoritePlaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favoritePlace: FavoritePlace)

    @Query("SELECT * FROM FavoritePlace")
    fun loadFavoritePlaces(): List<FavoritePlace>

    @Query("DELETE FROM FavoritePlace WHERE id = :id")
    fun deleteFavoritePlace(id: String)

}