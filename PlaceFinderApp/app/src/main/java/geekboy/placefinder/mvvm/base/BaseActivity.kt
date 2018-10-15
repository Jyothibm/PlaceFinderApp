package geekboy.placefinder.mvvm.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import dagger.android.AndroidInjection
import geekboy.placefinder.utils.other.NetworkUtils

abstract class BaseActivity<out V : ViewModel> : AppCompatActivity() {
    private lateinit var mViewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        mViewModel = getViewModel()
        super.onCreate(savedInstanceState)
    }

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getViewModel(): V

    fun isNetworkConnected(): Boolean = NetworkUtils.isNetworkConnected(applicationContext)

    fun hideKeyboard() {
        val view: View? = this.currentFocus
        val inputMethodManager: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}