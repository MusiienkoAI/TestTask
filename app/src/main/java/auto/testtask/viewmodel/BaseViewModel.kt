package auto.testtask.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import auto.testtask.utils.ErrorHandler
import auto.testtask.utils.SingleLiveEvent
import auto.utilities.actions.Next
import auto.utilities.actions.Error
import auto.utilities.entities.Resource
import auto.utilities.testing.OpenForTesting
import auto.utilities.actions.Complete
import auto.utilities.actions.Subscribe


import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import timber.log.Timber


@OpenForTesting
abstract class BaseViewModel : ViewModel() {

    val snackbarEvent: SingleLiveEvent<Int> by lazy { SingleLiveEvent<Int>() }
    val loadingEvent: SingleLiveEvent<Unit> by lazy { SingleLiveEvent<Unit>() }
    val successEvent: SingleLiveEvent<Unit> by lazy { SingleLiveEvent<Unit>() }
    val errorEvent: SingleLiveEvent<Throwable> by lazy { SingleLiveEvent<Throwable>() }

    protected val subscriptions: MutableList<Subscription> by lazy { mutableListOf<Subscription>() }
    protected val disposables: MutableList<Disposable> by lazy { mutableListOf<Disposable>() }

    override fun onCleared() {
        subscriptions.forEach { it.cancel() }
        subscriptions.clear()

        disposables.forEach { it.dispose() }
        disposables.clear()
        super.onCleared()
    }

    protected fun addSubscriptions(subscription: Subscription?) {
        subscription?.let { subscriptions.add(it) }
    }

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    protected fun <T> createSingleObserver(
        liveData: MutableLiveData<Resource<T>>? = null,
        next: Next<T>? = null,
        error: Error? = null,
        triggerLoadingState: Boolean = true
    ): SingleObserver<T> {
        return object : SingleObserver<T> {
            override fun onSuccess(t: T) {
                handleOnNext(t, liveData, next, triggerLoadingState)
            }

            override fun onSubscribe(d: Disposable) {
                handleOnSubscribe(d, liveData, triggerLoadingState)
            }

            override fun onError(e: Throwable) {
                handleOnError(e, liveData, error, triggerLoadingState)
            }
        }
    }

    protected fun createCompletable(complete: Complete, error: Error? = null): CompletableObserver {
        return object : CompletableObserver {
            override fun onComplete() {
                handleOnComplete<Any>(null, complete)
            }

            override fun onSubscribe(d: Disposable) {
                handleOnSubscribe<Any>(d, null, false)
            }

            override fun onError(e: Throwable) {
                handleOnError<Any>(e, null, error)
            }
        }
    }

    protected fun <T> createSubscriber(
        liveData: MutableLiveData<Resource<T>>? = null,
        subscribe: Subscribe,
        next: Next<T>? = null,
        error: Error? = null,
        complete: Complete? = null,
        triggerLoadingState: Boolean = true
    ): Subscriber<T> {
        return object : Subscriber<T> {
            override fun onComplete() {
                handleOnComplete(liveData, complete)
            }

            override fun onSubscribe(s: Subscription?) {
                handleOnSubscribe(s, subscribe, liveData, triggerLoadingState)
            }

            override fun onNext(t: T) {
                handleOnNext(t, liveData, next, triggerLoadingState)
            }

            override fun onError(t: Throwable?) {
                handleOnError(t, liveData, error, triggerLoadingState)
            }
        }
    }

    private fun <T> handleOnSubscribe(
        disposable: Disposable,
        liveData: MutableLiveData<Resource<T>>? = null,
        triggerLoadingState: Boolean = true
    ) {
        Timber.d("on subscribe triggered, disposable: $disposable, liveData: $liveData, triggerLoadingState: $triggerLoadingState")

        addDisposable(disposable)

        liveData?.value = Resource.loading()
        if (triggerLoadingState) loadingEvent.call()
    }

    private fun <T> handleOnSubscribe(
        subscription: Subscription?,
        subscribe: Subscribe,
        liveData: MutableLiveData<Resource<T>>? = null,
        triggerLoadingState: Boolean = true
    ) {
        Timber.d("on subscribe triggered, subscription: $subscription, subscribe: $subscribe, liveData: $liveData, triggerLoadingState: $triggerLoadingState")

        addSubscriptions(subscription)
        subscribe.invoke(subscription)

        liveData?.value = Resource.loading()
        if (triggerLoadingState) loadingEvent.call()
    }

    private fun <T> handleOnNext(
        value: T,
        liveData: MutableLiveData<Resource<T>>? = null,
        next: Next<T>? = null,
        triggerLoadingState: Boolean = true
    ) {
        Timber.d("on next triggered, data: $value, liveData: $liveData, next: $next, triggerLoadingState: $triggerLoadingState")

        if (triggerLoadingState) successEvent.call()
        if (next == null) liveData?.value = Resource.success(value)
        else next.invoke(value)
    }

    private fun <T> handleOnError(
        throwable: Throwable?,
        liveData: MutableLiveData<Resource<T>>? = null,
        error: Error? = null,
        triggerLoadingState: Boolean = true
    ) {
        val handledError = ErrorHandler.handleException(throwable)

        Timber.e("on error received $throwable, handled exception: $handledError, triggerLoadingState: $triggerLoadingState")

        if (triggerLoadingState) errorEvent.value = handledError
        if (error == null) liveData?.value = Resource.error(handledError)
        else error.invoke(handledError)
    }

    private fun <T> handleOnComplete(
        liveData: MutableLiveData<Resource<T>>? = null,
        complete: Complete? = null
    ) {
        Timber.d("on complete triggered, liveData: $liveData, complete: $complete")
        complete?.invoke()
    }
}