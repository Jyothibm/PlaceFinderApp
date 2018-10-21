package geekboy.placefinder

import androidx.room.Insert
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import geekboy.placefinder.repository.local.db.PlaceDb
import geekboy.placefinder.repository.local.db.favsearch.FavoritePlace
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class FavoritePlaceDaoTest {
    private lateinit var placeDb: PlaceDb

    @Before
    fun initDb() {
        placeDb = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), PlaceDb::class.java).build()
    }

    @Test
    fun insertFavoritePlace() {
        val cachedFavoritePlace = FavoritePlace("as123", "xyz", "abc", "1.0", true)
        placeDb.favoritePlaceDao().insert(cachedFavoritePlace)

        val favoritPlaceList = placeDb.favoritePlaceDao().loadFavoritePlaces()

        assert(favoritPlaceList.isNotEmpty())

    }

    @Test
    fun getFavoritePlaces() {

        val cachedFavoritePlace = arrayListOf(
            FavoritePlace("as123", "xyz", "abc", "1.0", true),
            FavoritePlace("ff123", "atm", "India", "1.0", true)
        )

        cachedFavoritePlace.forEach {
            placeDb.favoritePlaceDao().insert(it)
        }

        val listRetrivePlaces = placeDb.favoritePlaceDao().loadFavoritePlaces()
        assert(listRetrivePlaces == cachedFavoritePlace.sortedWith(compareBy({ it.id }, { it.id })))
    }

    @Test
    fun deleteUnFavoritePlace() {
        val cachedFavoritePlace = FavoritePlace("as123", "xyz", "abc", "1.0", true)
        placeDb.favoritePlaceDao().insert(cachedFavoritePlace)

        placeDb.favoritePlaceDao().deleteFavoritePlace(cachedFavoritePlace.id)
        assert(placeDb.favoritePlaceDao().loadFavoritePlaces().isEmpty())

    }

    @After
    fun closeDb() {
        placeDb.close()
    }
}