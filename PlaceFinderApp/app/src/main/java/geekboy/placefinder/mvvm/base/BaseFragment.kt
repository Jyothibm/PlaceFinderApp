package geekboy.placefinder.mvvm.base

import androidx.lifecycle.ViewModel
import androidx.fragment.app.Fragment
abstract class BaseFragment<out V : ViewModel> : Fragment() {
}