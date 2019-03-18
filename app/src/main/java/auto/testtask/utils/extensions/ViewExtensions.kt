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
import androidx.recyclerview.widget.RecyclerView


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

fun pagintantionListener(
    onDragging: (() -> Unit)? = null,
    onIdle: (() -> Unit)? = null,
    onSettling: (() -> Unit)? = null,
    onScrolledUp: (() -> Unit)? = null,
    onScrolledDown: (() -> Unit)? = null,
    onScrolledToTop: (() -> Unit)? = null,
    onScrolledToBottom: (() -> Unit)? = null
): RecyclerView.OnScrollListener {
    return  object : RecyclerView.OnScrollListener(){
        private var isOnTopOrBottom = false

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (!recyclerView.canScrollVertically(-1)) {
                isOnTopOrBottom = true
                onScrolledToTop?.invoke()
            } else if (!recyclerView.canScrollVertically(1)) {
                isOnTopOrBottom = true
                onScrolledToBottom?.invoke()
            } else if (dy < 0) {
                if (isOnTopOrBottom) {
                    onDragging?.invoke()
                }
                onScrolledUp?.invoke()
                isOnTopOrBottom = false
            } else if (dy > 0) {
                if (isOnTopOrBottom) {
                    onDragging?.invoke()
                }
                onScrolledDown?.invoke()
                isOnTopOrBottom = false
            }
        }


        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            when (newState) {
                RecyclerView.SCROLL_STATE_IDLE -> onIdle?.invoke()
                RecyclerView.SCROLL_STATE_DRAGGING -> if (!isOnTopOrBottom)
                    onDragging?.invoke()
                RecyclerView.SCROLL_STATE_SETTLING -> onSettling?.invoke()
            }
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