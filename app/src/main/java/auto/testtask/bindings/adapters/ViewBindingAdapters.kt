package auto.testtask.bindings.adapters

import androidx.databinding.BindingAdapter
import android.view.View
import auto.utilities.entities.State



@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("goneUnlessState")
fun goneUnlessState(view: View, state: State) {
    view.visibility = if (state is State.LOADING) View.VISIBLE else View.GONE
}