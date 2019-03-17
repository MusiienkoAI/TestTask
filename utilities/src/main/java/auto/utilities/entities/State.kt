package auto.utilities.entities


sealed class State(val throwable: Throwable? = null) {
    object LOADING : State()
    object SUCCESS : State()
    data class ERROR(private val t: Throwable?) : State(t)
}