package auto.testtask.utils



import auto.utilities.exceptions.BaseThrowable
import auto.utilities.exceptions.UnknownError
import auto.utilities.extensions.asJsonArray
import auto.utilities.extensions.isNotEmptyOrBlank
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import timber.log.Timber


object ErrorHandler {
    fun handleException(t: Throwable?): BaseThrowable {
        val error = when (t) {
            is HttpException -> when (t.code()) {
//                422 -> t.response().errorBody()?.string()?.let { if (it.isNotEmptyOrBlank()) ValidationError(it.asJsonArray()) else null }
//                    ?: UnknownError(
//                        rootCause = t
//                    )
                503 -> UnknownError(rootCause = t) //Maintenance
                else -> UnknownError(rootCause = t)
            }
            is BaseThrowable -> t
            else -> UnknownError(rootCause = t)
        }

        Timber.d("handling exception, received error: $t, handled error: $error")

        return error
    }
}