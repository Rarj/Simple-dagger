package grack.dev.moviedagger

import android.annotation.SuppressLint
import android.os.StrictMode
import android.widget.RatingBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import grack.dev.moviedagger.utils.CircleImageView
import grack.dev.moviedagger.utils.Common.getBitmap
import grack.dev.moviedagger.utils.Common.setImage
import grack.dev.moviedagger.utils.Common.setImageCircle
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
  fun setUrlImage(view: AppCompatImageView, url: String?) {
    setImage(view, url, view)
  }

  @BindingAdapter(("android:urlCircle"))
  @JvmStatic
  fun setUrlImageCircle(view: CircleImageView, url: String?) {
    setImageCircle(view, url, view)
  }

  @BindingAdapter(("android:blur"))
  @JvmStatic
  fun setImageBlur(view: AppCompatImageView, url: String?) {
    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)
    Blurry.with(view.context).from(getBitmap(view, url)).into(view)
  }

  @SuppressLint("SimpleDateFormat")
  @BindingAdapter(("android:date"))
  @JvmStatic
  fun setSimpleDate(view: AppCompatTextView, releaseDate: String?) {
    if (releaseDate.isNullOrEmpty()) {
      view.text = "null"

    } else {
      val pattern = SimpleDateFormat("yyyy-MM-dd")
      val date: Date? = pattern.parse(releaseDate)
      view.text = SimpleDateFormat("EEEE, dd MMMM yyyy").format(date)
    }
  }

  @BindingAdapter(("android:overview"))
  @JvmStatic
  fun setOverview(view: AppCompatTextView, overview: String) {
    if (overview.isNullOrEmpty()) {
      view.text = view.resources.getString(R.string.detail_no_description_caption)
    } else {
      view.text = overview
    }
  }

  @BindingAdapter(("android:gender"))
  @JvmStatic
  fun setGender(view: AppCompatTextView, gender: Int) {
    when (gender) {
      1 -> view.text = "Female"
      2 -> view.text = "Male"
      else -> view.text = "Not Defined"
    }
  }

}