package geekboy.placefinder.mvvm.recentsearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import geekboy.placefinder.data.AppDataManager
import geekboy.placefinder.repository.local.db.recentsearch.RecentSearch
import geekboy.placefinder.repository.local.db.recentsearch.RecentSearchDao
import kotlinx.coroutines.experimental.*
import javax.inject.Inject

class RecentSearchViewModel
@Inject constructor(val appDataManager: AppDataManager) : ViewModel() {

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var recentSearchList = MutableLiveData<ArrayList<RecentSearch>>()

    val recentSearchDao = appDataManager.getRecentSearchDao()

    fun getRecentSearchData() {
        uiScope.launch {
            if (recentSearchDao.getAllRecentSearch().value != null) {
                recentSearchList.value =
                        recentSearchDao.getAllRecentSearch().value as ArrayList<RecentSearch>
            } else {
                recentSearchList.value =
                        ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}