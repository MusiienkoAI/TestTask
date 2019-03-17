package auto.utilities.exceptions

class UnknownError(
    rootCause: Throwable?,
    message: String = ""
) : BaseThrowable(rootCause, message)