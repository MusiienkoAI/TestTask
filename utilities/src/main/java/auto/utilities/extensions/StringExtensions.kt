package auto.utilities.extensions

import com.google.gson.*
import java.util.regex.Pattern

/** https://stackoverflow.com/questions/9769554/how-to-convert-number-into-k-thousands-m-million-and-b-billion-suffix-in-jsp
 * Converts the number to K, M suffix
 * Example: 5500 will be displayed as 5.5k
 **/
fun convertToSuffix(count: Long): String {
    if (count < 1000) return "" + count
    val exp = (Math.log(count.toDouble()) / Math.log(1000.0)).toInt()
    return String.format("%.1f%c", count / Math.pow(1000.0, exp.toDouble()), "kmgtpe"[exp - 1])
}

fun CharSequence.isEmptyOrBlank(): Boolean {
    return isEmpty() || isBlank()
}

fun CharSequence.isNotEmptyOrBlank(): Boolean {
    return !isEmptyOrBlank()
}

fun CharSequence?.isNullOrEmptyOrBlank(): Boolean {
    return this == null || isEmptyOrBlank()
}

fun String.asJsonElement(): JsonElement? {
    return try {
        if (isEmptyOrBlank()) null else JsonParser().parse(this)
    } catch (e: Exception) {
        e.printStackTrace()
        when (e) {
            is JsonParseException -> null
            else -> throw e
        }
    }
}

fun String.asJsonObject(): JsonObject {
    return asJsonObjectOrNull()
        ?: throw IllegalStateException("given string is not a valid JSON")
}

fun String.asJsonObjectOrNull(): JsonObject? {
    return try {
        asJsonElement()?.asJsonObject
    } catch (e: Exception) {
        e.printStackTrace()
        when (e) {
            is IllegalStateException -> null
            else -> throw e
        }
    }
}

fun String.asJsonArray(): JsonArray {
    return asJsonArrayOrNull()
        ?: if (asJsonElement() != null) toJsonArray()!!
        else throw IllegalStateException("given string is not a valid JSON")
}

fun String.asJsonArrayOrNull(): JsonArray? {
    return try {
        asJsonElement()?.asJsonArray
    } catch (e: java.lang.Exception) {
        when (e) {
            is IllegalStateException -> null
            is JsonParseException -> null
            else -> throw e
        }
    }
}

fun String.toJsonArray(): JsonArray? {
    return try {
        JsonArray().apply { add(this@toJsonArray.asJsonElement()) }
    } catch (e: java.lang.Exception) {
        when (e) {
            is IllegalStateException -> null
            is JsonParseException -> null
            else -> throw e
        }
    }
}

fun String?.isEmailValid(): Boolean {
    val pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    return (this != null) && pattern.matcher(this).matches()
}

