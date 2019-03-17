package auto.testtask.utils.extensions

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat


fun Context.getFont(fontId: Int): Typeface? {
    return ResourcesCompat.getFont(this, fontId)
}