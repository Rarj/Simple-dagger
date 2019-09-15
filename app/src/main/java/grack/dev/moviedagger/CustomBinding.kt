package grack.dev.moviedagger

import android.annotation.SuppressLint
import android.os.StrictMode
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import grack.dev.moviedagger.utils.Common.getBitmap
import grack.dev.moviedagger.utils.Common.setImage
import jp.wasabeef.blurry.Blurry
import java.text.SimpleDateFormat
import java.util.*

object CustomBinding {

  @BindingAdapter("android:rate")
  @JvmStatic
  fun setRating(view: RatingBar, rating: Float) {
    if (view.rating != rating) {
      view.rating = (rating / 2.0).toFloat()
    }
  }

  @BindingAdapter(("android:url"))
  @JvmStatic
  fun setUrlImage(view: ImageView, url: String) {
    setImage(view, url, view)
  }

  @BindingAdapter(("android:blur"))
  @JvmStatic
  fun setImageBlur(view: ImageView, url: String) {
    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)
    Blurry.with(view.context).from(getBitmap(url)).into(view)
  }

  @SuppressLint("SimpleDateFormat")
  @BindingAdapter(("android:date"))
  @JvmStatic
  fun setSimpleDate(view: TextView, releaseDate: String) {
    val pattern = SimpleDateFormat("yyyy-MM-dd")
    val date: Date? = pattern.parse(releaseDate)
    view.text = SimpleDateFormat("EEEE, dd MMMM yyyy").format(date)
  }

}