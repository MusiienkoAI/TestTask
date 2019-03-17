package auto.utilities.extensions



import auto.utilities.entities.Resource
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.HttpException


fun <T> Observable<T>.toResult(): Observable<Resource<T>> {
    return map { Resource.fromData(it) }
}

fun <T> Observable<Resource<T>>.fromResult(): Observable<T> {
    return map { it.data }
}

fun <T> Single<Resource<T>>.fromResult(): Single<T> {
    return map { it.data }
}

fun <T> Observable<Resource<T>>.onlySuccess(): Observable<T> {
    return filter { it.isSuccess() }
        .map { it.data!! }
}

fun <T> Observable<Resource<T>>.onlyError(): Observable<Throwable> {
    return filter { it.isError() }
        .map { it.state?.throwable!! }
}

fun <T> Observable<Resource<T>>.onlyHttpException(): Observable<HttpException> {
    return filter { it.isError() && it.state?.throwable is HttpException }
        .map { it.state?.throwable as HttpException }
}

fun <T> Observable<Resource<T>>.onlyHttpException(code: Int): Observable<HttpException> {
    return onlyHttpException()
        .filter { it.code() == code }
}

fun <T> Observable<Resource<T>>.onlyHttpExceptionExcluding(vararg codes: Int): Observable<HttpException> {
    return onlyHttpException()
        .filter { codes.contains(it.code()) }
}