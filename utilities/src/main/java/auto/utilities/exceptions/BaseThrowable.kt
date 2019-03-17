package auto.utilities.exceptions


abstract class BaseThrowable(
    public val rootCause: Throwable? = null,
    message: String = ""
) : Throwable(message, rootCause)