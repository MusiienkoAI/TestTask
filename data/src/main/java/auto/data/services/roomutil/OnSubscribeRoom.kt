package auto.data.services.roomutil

import auto.data.db.CarDatabase
import auto.data.entities.RoomNullable
import io.reactivex.FlowableEmitter
import io.reactivex.FlowableOnSubscribe
import io.reactivex.functions.Cancellable
import java.util.concurrent.atomic.AtomicBoolean


abstract class OnSubscribeRoom<Data>(private val roomDb: CarDatabase) : FlowableOnSubscribe<RoomNullable<Data?>> {
    private val isCanceled = AtomicBoolean()
    private val emitters = mutableListOf<FlowableEmitter<RoomNullable<Data?>>>()
    private val lock = Any()

    override fun subscribe(emitter: FlowableEmitter<RoomNullable<Data?>>) {
        synchronized(lock) {
            if (!isCanceled.get() && emitters.isNotEmpty()) {
                emitter.setCancellable(createCancellable(emitter))
                emitters.add(emitter)
                return
            } else if (isCanceled.get()) {
                return
            }
        }
        emitter.setCancellable(createCancellable(emitter))
        emitters.add(emitter)

        var withError = false
        var data: Data? = null

        try {
            if (!isCanceled.get()) {
                roomDb.beginTransaction()
                data = get()
                if (!isCanceled.get()) roomDb.setTransactionSuccessful()
            }
        } catch (e: Exception) {
            when (e) {
                is RuntimeException, Error() -> {
                    withError = true
                    sendOnError(e)
                }
                else -> throw e
            }
        } finally {
            roomDb.endTransaction()
        }

        if (!isCanceled.get() && !withError) sendOnNext(RoomNullable(data))

        if (!withError) sendOnCompleted()

        isCanceled.set(false)
    }

    private fun sendOnNext(data: RoomNullable<Data?>) {
        for (emitter in emitters) {
            if (!emitter.isCancelled) emitter.onNext(data)
        }
    }

    private fun sendOnError(e: Throwable) {
        for (emitter in emitters) {
            if (!emitter.isCancelled) emitter.onError(e)
        }
    }

    private fun sendOnCompleted() {
        for (emitter in emitters) {
            if (!emitter.isCancelled) emitter.onComplete()
        }
    }

    private fun createCancellable(emitter: FlowableEmitter<RoomNullable<Data?>>): Cancellable {
        return Cancellable {
            synchronized(lock) {
                emitters.remove(emitter)
                if (emitters.isEmpty()) isCanceled.set(true)
            }
        }
    }

    abstract fun get(): Data?
}