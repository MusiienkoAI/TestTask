package auto.testtask.utils.extensions

import androidx.lifecycle.*
import androidx.fragment.app.Fragment


inline fun <reified T : ViewModel> Fragment.getViewModel(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}

/**
 * Example:
 * withViewModel<PostListViewModel>(viewModelFactory) {
 * observe(posts, ::updatePosts)
 * swipeRefreshLayout.setOnRefreshListener { get(refresh = true) }
}
 */
inline fun <reified T : ViewModel> Fragment.withViewModel(
    viewModelFactory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T {
    val vm = getViewModel<T>(viewModelFactory)
    vm.body()
    return vm
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}