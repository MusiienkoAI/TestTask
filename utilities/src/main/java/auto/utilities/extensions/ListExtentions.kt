package auto.utilities.extensions


fun <T> List<T>.isRequestAllowed(maxPage: Int, pageSize: Int): Boolean {
    return when {
        isEmpty() -> true
        ((size.toFloat() / pageSize) <= (maxPage - 1)) -> true
        else -> false
    }
}