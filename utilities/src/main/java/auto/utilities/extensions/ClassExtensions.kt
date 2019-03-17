package auto.utilities.extensions


inline fun <reified T : Any> T.javaClass(): Class<T> = T::class.java
