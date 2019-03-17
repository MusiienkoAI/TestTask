package auto.utilities.exceptions

class RoomError(
    rootCause: Throwable = Throwable(),
    message: String = ""
) : BaseThrowable(rootCause, message)