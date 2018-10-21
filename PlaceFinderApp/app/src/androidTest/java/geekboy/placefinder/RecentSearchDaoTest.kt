package geekboy.placefinder

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import geekboy.placefinder.repository.local.db.PlaceDb
import geekboy.placefinder.repository.local.db.recentsearch.RecentSearch
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RecentSearchDaoTest {
    private lateinit var placeDb: PlaceDb

    @Before
    fun initDb() {
        placeDb = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), PlaceDb::class.java).build()
    }

    @Test
    fun insertRecentSearch(){
        val cachedRecentSearch = RecentSearch(123,"Foo")
        placeDb.recentSearchDao().insert(cachedRecentSearch)
        val recentSearch = placeDb.recentSearchDao().getAllRecentSearch()
        assert(recentSearch.isNotEmpty())
    }

    @Test
    fun getRecentSearch() {
        val recentSearchArray = arrayOf(
            RecentSearch(1,"Foo"),
            RecentSearch(2,"BAR"),
            RecentSearch(3,"buzz")
        )
        recentSearchArray.forEach {
            placeDb.recentSearchDao().insert(it)
        }

        val retrieveRecentSearch = placeDb.recentSearchDao().getAllRecentSearch()
        assert(retrieveRecentSearch == recentSearchArray.sortedWith(compareBy({it.id},{it.id})))
    }

    @After
    fun closeDb() {
        placeDb.close()
    }

}
