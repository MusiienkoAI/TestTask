package auto.utilities.extensions

import java.text.SimpleDateFormat
import java.util.*


var Calendar.year: Int
    get() = get(Calendar.YEAR)
    set(value) = set(Calendar.YEAR, value)

var Calendar.dayOfMonth: Int
    get() = get(Calendar.DAY_OF_MONTH)
    set(value) = set(Calendar.DAY_OF_MONTH, value)

var Calendar.monthOfYear: Int
    get() = get(Calendar.MONTH)
    set(value) = set(Calendar.MONTH, value)

var Calendar.hourOfDay: Int
    get() = get(Calendar.HOUR_OF_DAY)
    set(value) = set(Calendar.HOUR_OF_DAY, value)

var Calendar.minute: Int
    get() = get(Calendar.MINUTE)
    set(value) = set(Calendar.MINUTE, value)

val Calendar.fullMonthName: String
    get() = parseTime("LLLL")

val Calendar.fullMonthName_I8N: String
    get() = parseTime("MMMM")

val Calendar.shortenMonthName: String
    get() = parseTime("LLL")

val Calendar.shortenMonthName_I8N: String
    get() = parseTime("MMM")

private fun Calendar.parseTime(timePattern: String): String =
    SimpleDateFormat(timePattern, Locale.getDefault()).format(this.time)
