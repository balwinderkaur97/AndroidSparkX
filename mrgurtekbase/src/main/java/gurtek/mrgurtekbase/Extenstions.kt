package gurtek.mrgurtekbase

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.LayoutRes
import android.support.design.R
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.util.Base64
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import gurtek.mrgurtekbase.adapter.BaseViewHolder
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * * Created by Gurtek Singh on 1/1/2018.
 * Sachtech Solution
 * gurtek@protonmail.ch
 */

fun ViewGroup.inflate(@LayoutRes view: Int): View {
    return LayoutInflater.from(context).inflate(view, this, false)
}

/*fun ImageView.setByteimage(base64string: String?) {

    *//* if (base64string == null || base64string.isEmpty()) {
         setImageResource(R.drawable.dummyimage)
     }*//*

    val data = stringToBytes(base64string)
    val decodeByteArray = BitmapFactory.decodeByteArray(data, 0, data.size)
    // if (decodeByteArray == null) setImageResource(R.drawable.dummyimage)
    setImageBitmap(decodeByteArray)
}*/

fun stringToBytes(base64string: String?): ByteArray? {
    return null
}

/*fun String.byteToBimap(): Bitmap? {

    if (this.isEmpty()) return null

    val data = stringToBytes(this)
    return BitmapFactory.decodeByteArray(data, 0, data.size)
}*/

fun Date.toReadable(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    return formatter.format(this)
}

fun String.name(): String {
    return this.split("@")[0]
}

fun Spinner.isValueSelected(textView: TextView): Boolean {
    val isSelected = selectedItemPosition != 0
    if (isSelected) textView.setTextColor(Color.DKGRAY)
    else textView.setTextColor(Color.RED)
    return isSelected
}

fun EditText.isTextEnter(error: String): Boolean {

    return if (text.toString().isEmpty()) {
        this.error = error
        false
    } else {
        true
    }
}

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

fun Int.string() {

}

fun String.toFile(): File {
    return File(this)
}

fun Int.toBoolean(): Boolean {
    return this == 1
}

fun String.toast(context: Context?) {
    Toast.makeText(context, this, Toast.LENGTH_LONG).show()
}

fun ByteArray.toStringBytes(): String? {
    return Base64.encodeToString(this, Base64.DEFAULT)
}

fun Activity.hideKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.currentFocus.windowToken, 0)
}

fun Int.toColor(context: Context): Int {
    return ContextCompat.getColor(context, this)
}


fun Int.snakbar(view: View) {
    view.context.getString(this).snakbar(view)
}

fun String.snakbar(view: View) {
    val snackbar = Snackbar.make(view, this, Snackbar.LENGTH_SHORT)
    snackbar.makeCustomize()
    snackbar.show()
}

fun Snackbar.makeCustomize() {
    val snackbarLayout = view as Snackbar.SnackbarLayout

    changeParentLayoutParams(snackbarLayout)

    changeTextAligement(snackbarLayout)


}

private fun changeParentLayoutParams(snackbarContentLayout: Snackbar.SnackbarLayout) {
    val layoutParams = snackbarContentLayout.layoutParams as FrameLayout.LayoutParams
    layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
}

private fun changeTextAligement(snackbarLayout: Snackbar.SnackbarLayout) {
    val textView = snackbarLayout.findViewById<TextView>(R.id.snackbar_text)
    chageTextGravity(textView)
    val layoutParams = textView.layoutParams
    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT

}

private fun chageTextGravity(textView: TextView) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
    }

    textView.gravity = Gravity.CENTER_HORIZONTAL

}


inline fun <reified T> parcelableCreator(
        crossinline create: (Parcel) -> T) =
        object : Parcelable.Creator<T> {
            override fun createFromParcel(source: Parcel) = create(source)
            override fun newArray(size: Int) = arrayOfNulls<T>(size)
        }

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.holder(): Pair<Int, BaseViewHolder> {

    val viewHolder = tag as BaseViewHolder
    val position = viewHolder.adapterPosition

    return Pair(position,viewHolder)

}





