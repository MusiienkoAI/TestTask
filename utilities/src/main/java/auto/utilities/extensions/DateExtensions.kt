package auto.utilities.extensions

import auto.utilities.Constants
import org.joda.time.DurationFieldType
import org.joda.time.LocalDate
import org.joda.time.Years
import timber.log.Timber
import java.util.*


fun Date.isValidBirthday(): Boolean {
    val isValid = this.yearsBetweenNow >= Constants.MIN_AGE
    Timber.d("is valid birthday: $isValid, birthday in millis: $time")
    return isValid
}

fun Date.isValidDocument(): Boolean {
    val isValid = time > LocalDate().toDate().time + 1000
    return isValid
}

val Date.yearsBetweenNow: Int
    get() = Years.yearsBetween(LocalDate(time), LocalDate()).get(DurationFieldType.years())