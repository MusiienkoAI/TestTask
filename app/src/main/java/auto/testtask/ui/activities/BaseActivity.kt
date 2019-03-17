package auto.testtask.ui.activities


import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import auto.testtask.R
import auto.testtask.databinding.ActivityBaseBinding
import auto.testtask.viewmodel.BaseViewModel
import auto.utilities.entities.State
import auto.utilities.extensions.getColorFromRes
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import javax.inject.Inject


abstract class BaseActivity : AppCompatActivity() {

    @Inject
    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var baseBinding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    fun <T : BaseViewModel> subscribeToViewModelEvents(viewModel: T) {
        Timber.d("subscribing to view model events, viewModel:$viewModel")
        viewModel.snackbarEvent.observe(this, Observer { showSnackbar(it) })
        viewModel.loadingEvent.observe(this, Observer { processResponseStatus(State.LOADING) })
        viewModel.successEvent.observe(this, Observer { processResponseStatus(State.SUCCESS) })
        viewModel.errorEvent.observe(this, Observer { processResponseStatus(State.ERROR(it)) })
    }

    fun showSnackbar(@StringRes stringRes: Int?) {
        Timber.d("showing snackbar, string resource: $stringRes")
        if (stringRes == null) {
            Timber.w("string resource is null")
            return
        }

        val snackbar = Snackbar.make(
            baseBinding.root,
            stringRes,
            Snackbar.LENGTH_LONG
        )
        snackbar.view.apply {
            findViewById<TextView>(com.google.android.material.R.id.snackbar_text).apply {
                gravity = Gravity.CENTER_HORIZONTAL
            }
            setBackgroundColor(context.getColorFromRes(R.color.colorAccent))
        }
        snackbar.show()
    }

    fun showLoading() {
        Timber.d("showing progress bar")
        baseBinding.baseActivityLoadProgressBar.visibility = View.VISIBLE
    }

    fun hideLoading() {
        Timber.d("hiding progress bar")
        baseBinding.baseActivityLoadProgressBar.visibility = View.GONE
    }

    fun processResponseStatus(state: State?) {
        Timber.d("processing response status: $state")
        baseBinding.state = state
    }

    protected fun <T : ViewDataBinding> setDataBindingContentView(@LayoutRes resId: Int): T {
        Timber.d("setting data binding content view")
        return DataBindingUtil.inflate(layoutInflater, resId, baseBinding.baseActivityBaseContentLayout, true)
    }


}