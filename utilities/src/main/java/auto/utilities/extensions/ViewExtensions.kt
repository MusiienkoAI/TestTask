@file:Suppress("unused")
@file:JvmName("ViewUtils")

package auto.utilities.extensions

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView



val View.isVisible: Boolean
    get() = visibility == View.VISIBLE

fun View.makeVisible() {
    if (visibility != View.VISIBLE) visibility = View.VISIBLE
}

fun View.makeInvisible() {
    if (visibility != View.INVISIBLE) visibility = View.INVISIBLE
}

fun View.makeGone() {
    if (visibility != View.GONE) visibility = View.GONE
}

fun View.setVisible(visible: Boolean) {
    if (visible) makeVisible() else makeGone()
}

fun View.enable() {
    if (!isEnabled) isEnabled = true
}

fun View.disable() {
    if (isEnabled) isEnabled = false
}

fun EditText.clearAndDisable() {
    disable()
    setText("")
}

fun View.setDisabled(disable: Boolean) {
    if (disable) disable() else enable()
}

fun Context.getColorFromRes(@ColorRes colorInt: Int): Int {
    return ContextCompat.getColor(this, colorInt)
}

fun Context.getDrawableFromRes(@DrawableRes drawableResId: Int): Drawable? {
    return ContextCompat.getDrawable(this, drawableResId)
}


fun TextView.setTextResColor(@ColorRes colorInt: Int) {
    setTextColor(context.getColorFromRes(colorInt))
}

fun TextView.setTextIfNotEmpty(@StringRes textResId: Int?) {
    if (textResId == null || textResId == 0) {
        makeGone()
        return
    }
    setText(textResId)
    if (text.isEmpty()) makeGone()
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s.toString())
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    })
}

fun EditText.validate(validator: (String) -> Boolean, errorMessage: String) {
    this.afterTextChanged {
        this.error = if (validator(it)) null else errorMessage
    }
    this.error = if (validator(this.text.toString())) null else errorMessage
}

val Activity.screenWidth: Int
    get() {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

val Activity.screenHeight: Int
    get() {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

fun View.dpToPx(dp: Int): Int {
    return ((dp * Resources.getSystem().displayMetrics.density).toInt())
}

fun View.pxToDp(px: Int): Int {
    return (px / Resources.getSystem().displayMetrics.density).toInt()
}

fun EditText.doOnNextBtnPress(onButtonPressed: (View) -> Unit) {
    setOnEditorActionListener { v, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            onButtonPressed.invoke(v)
        }
        return@setOnEditorActionListener false
    }
}

fun Window.makeFullScreen() {
    setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
}

fun Context.getDimensionPixelSize(@DimenRes dimenResId: Int): Int {
    return resources.getDimensionPixelSize(dimenResId)
}

val Activity.rootView: View
    get() = (window.decorView.findViewById(android.R.id.content) as ViewGroup).getChildAt(0)