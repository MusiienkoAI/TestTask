package auto.test_utilities

import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing
import org.mockito.stubbing.Stubber
import kotlin.reflect.KClass


inline fun <reified T> mock(): T = Mockito.mock(T::class.java)

inline fun <reified T> spy(init: () -> T): T = Mockito.spy(init.invoke())

fun <T> whenever(methodCall: T): OngoingStubbing<T> = Mockito.`when`(methodCall)

fun <T> Stubber.whenever(methodCall: T): T = `when`(methodCall)

fun <T> Class<T>.argumentCapture(): T = argumentCaptor().capture()

fun <T> Class<T>.argumentCaptor(): ArgumentCaptor<T> = ArgumentCaptor.forClass(this)

@Suppress("CAST_NEVER_SUCCEEDS")
inline fun <reified T : Any> any() = Mockito.any(T::class.java) ?: createInstance()

inline fun <reified T : Any> createInstance(): T {
    return createInstance(T::class)
}

fun <T : Any> createInstance(kClass: KClass<T>): T {
    return castNull()
}

@Suppress("UNCHECKED_CAST")
private fun <T> castNull(): T = null as T