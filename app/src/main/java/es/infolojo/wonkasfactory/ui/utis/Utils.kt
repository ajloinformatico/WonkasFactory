package es.infolojo.wonkasfactory.ui.utis

import android.content.Context
import android.widget.Toast

object Utils {
    fun showToast(context: Context, text: String, duration: Int) {
        Toast.makeText(context, text, duration).show()
    }
}