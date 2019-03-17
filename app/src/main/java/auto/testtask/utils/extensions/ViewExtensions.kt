package auto.testtask.utils.extensions

import android.annotation.SuppressLint
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.text.Spanned




@SuppressLint("NewApi")
fun TextView.setFont(fontId: Int) {
    typeface = if (isInEditMode) context.resources.getFont(fontId) else context.getFont(fontId)
}


fun keyListener(
    onBackPressed: (() -> Unit)? = null
): View.OnKeyListener {
    return View.OnKeyListener { _, keyCode, event ->
        if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
            onBackPressed?.invoke()
        }
        false
    }
}


fun PhoneMaskTextWatcher(view: EditText): TextWatcher {
    return object : TextWatcher {
        var len = 0

        override fun afterTextChanged(s: Editable?) {
            val i = view.text.toString().length
            if (i < 5)
                len = 0
            if (i == 5 && len < 6) {
                len = 6
                val ss = s.toString()
                val first = ss.substring(0, ss.length - 1)
                val last = ss.substring(ss.length - 1)
                view.setText("$first-$last")
                view.setSelection(view.text.toString().length)
            }

        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }
    }
}


fun MaxLinesInputFilter(maxLines: Int): InputFilter {
    return object : InputFilter {

        override fun filter(
            source: CharSequence,
            start: Int,
            end: Int,
            dest: Spanned,
            dstart: Int,
            dend: Int
        ): CharSequence? {
            val newLinesToBeAdded = countOccurrences(source.toString(), '\n')
            val newLinesBefore = countOccurrences(dest.toString(), '\n')
            return if (newLinesBefore >= maxLines - 1 && newLinesToBeAdded > 0) {
                // filter
                ""
            } else null

            // do nothing
        }

        /**
         * @return the maximum lines enforced by this input filter
         */
        fun getMax(): Int {
            return maxLines
        }

        /**
         * Counts the number occurrences of the given char.
         *
         * @param string the string
         * @param charAppearance the char
         * @return number of occurrences of the char
         */
        fun countOccurrences(string: String, charAppearance: Char): Int {
            var count = 0
            for (i in 0 until string.length) {
                if (string[i] == charAppearance) {
                    count++
                }
            }
            return count
        }
    }
}