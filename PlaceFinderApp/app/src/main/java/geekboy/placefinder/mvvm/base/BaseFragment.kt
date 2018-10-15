package geekboy.placefinder.mvvm.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<out V : ViewModel> : Fragment() {
    private var mActivity: BaseActivity<*>? = null
    private var mViewModel: V? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()
        setHasOptionsMenu(false)
    }

    override fun onAttach(context: Context?) {
        performDependencyInjection()
        super.onAttach(context)

    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    fun getBaseActivity(): BaseActivity<*>? {
        return mActivity
    }


    fun isNetworkConnected(): Boolean {
        return mActivity != null && mActivity!!.isNetworkConnected()
    }

    fun hideKeyboard() {
        if (mActivity != null) {
            mActivity!!.hideKeyboard()
        }
    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }


    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getViewModel(): V

}