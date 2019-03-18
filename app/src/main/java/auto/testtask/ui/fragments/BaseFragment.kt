package auto.testtask.ui.fragments


import android.os.Bundle
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


import androidx.navigation.fragment.findNavController
import auto.testtask.ui.activities.BaseActivity
import auto.testtask.viewmodel.BaseViewModel
import auto.utilities.entities.State
import auto.utilities.testing.OpenForTesting

import javax.inject.Inject


@Suppress("RedundantVisibilityModifier")
@OpenForTesting
abstract class BaseFragment : Fragment() {
    @Inject
    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    public lateinit var viewModelFactory: ViewModelProvider.Factory
    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    public val baseActivity: BaseActivity
        get() = activity as BaseActivity

    abstract fun subscribeToModel()
    abstract fun initViewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        subscribeToModel()
    }

    protected fun <T : BaseViewModel> subscribeToViewModelEvents(viewModel: T) {
        viewModel.snackbarEvent.observe(this, Observer { showSnackbar(it) })
        viewModel.loadingEvent.observe(this, Observer { processResponseStatus(State.LOADING) })
        viewModel.successEvent.observe(this, Observer { processResponseStatus(State.SUCCESS) })
        viewModel.errorEvent.observe(this, Observer { processResponseStatus(State.ERROR(it)) })
    }

    public fun showSnackbar(@StringRes stringRes: Int?) {
        baseActivity.showSnackbar(stringRes)
    }

    public fun showLoading() {
        baseActivity.showLoading()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    public fun hideLoading() {
        baseActivity.hideLoading()
    }

    public fun navController() = findNavController()

    private fun processResponseStatus(state: State?) {
        baseActivity.processResponseStatus(state)
    }

    protected fun popBackStack() {
        navController().popBackStack()
    }
}