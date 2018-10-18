package geekboy.placefinder.mvvm.recentsearch

import android.content.Context
import androidx.lifecycle.ViewModelProviders

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dagger.android.support.AndroidSupportInjection

import geekboy.placefinder.R
import geekboy.placefinder.databinding.RecentSearchFragmentBinding
import geekboy.placefinder.mvvm.base.BaseFragment
import geekboy.placefinder.utils.autoCleared
import geekboy.placefinder.viewmodel.AppViewModelFactory
import javax.inject.Inject

class RecentSearchFragment : BaseFragment<RecentSearchViewModel>() {

    companion object {
        fun newInstance() = RecentSearchFragment()
    }

    private lateinit var viewModel: RecentSearchViewModel
    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory

    lateinit var binding: RecentSearchFragmentBinding
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil
            .inflate(
                inflater,
                R.layout.recent_search_fragment,
                container,
                false
            )

        binding.recentSearchViewModel = viewModel
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getRecentSearchData()

        viewModel.recentSearchList.observe(this, Observer {
            if (it != null && it.size > 0) {
                binding.rvRecentSearch.adapter = RecentSearchAdapter(it)
            }
        })

    }

    override fun getViewModel(): RecentSearchViewModel {
        viewModel = ViewModelProviders.of(this, appViewModelFactory).get(RecentSearchViewModel::class.java)
        return viewModel
    }

    fun loadRecentSearch() {
        viewModel.getRecentSearchData()
    }

}
