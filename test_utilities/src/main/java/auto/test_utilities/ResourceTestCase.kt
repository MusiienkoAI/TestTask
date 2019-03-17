package auto.test_utilities

import android.annotation.TargetApi
import android.os.Build
import android.os.LocaleList
import androidx.test.InstrumentationRegistry.getContext
import junit.framework.TestCase
import java.util.*


abstract class ResourceTestCase : TestCase() {
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected fun setLocale(language: String, country: String) {
        val locale = Locale(language, country).also { Locale.setDefault(it) }
        val resources = getContext().resources
        val configuration = resources.configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.locales = LocaleList(locale)
        } else {
            configuration.setLocale(locale)
        }
        getContext().createConfigurationContext(configuration)
    }
}