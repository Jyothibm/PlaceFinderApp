package geekboy.placefinder.repository.local.db.recentsearch

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecentSearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recentSearch: RecentSearch)

    @Query("SELECT * FROM RecentSearch")
    fun getAllRecentSearch(): LiveData<List<RecentSearch>>
}