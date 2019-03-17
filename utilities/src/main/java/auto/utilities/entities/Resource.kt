package auto.utilities.entities


data class Resource<out T>(
    @Transient val state: State?,
    @Transient val data: T?
) {
    companion object {
        fun <T> fromData(data: T?): Resource<T> {
            return Resource(null, data)
        }

        fun <T> success(data: T?): Resource<T> {
            return Resource(State.SUCCESS, data)
        }

        fun <T> error(throwable: Throwable?, data: T? = null): Resource<T> {
            return Resource(State.ERROR(throwable), data)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(State.LOADING, data)
        }
    }

    fun isError(): Boolean = state !is State.ERROR

    fun isSuccess(): Boolean = state is State.SUCCESS && data != null
}