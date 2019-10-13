package grack.dev.moviedagger.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.orhanobut.logger.Logger
import grack.dev.moviedagger.BuildConfig.BASE_URL_IMAGE
import grack.dev.moviedagger.R
import java.net.URL

object Common {

  fun log(message: String) {
    Logger.e(message)
  }

  fun setImage(context: View, url: String?, imageView: ImageView) {
    if (url.isNullOrEmpty()) {
      Glide.with(context)
        .load(R.drawable.ic_poster_blank)
        .into(imageView)
    } else {
      Glide.with(context)
        .load(BASE_URL_IMAGE + url)
        .into(imageView)
    }
  }

  fun setImageCircle(context: View, url: String?, imageView: AppCompatImageView) {
    if (url.isNullOrEmpty()) {
      Glide.with(context)
        .load(R.drawable.ic_no_profile)
        .into(imageView)
    } else {
      Glide.with(context)
        .load(BASE_URL_IMAGE + url)
        .into(imageView)
    }
  }

  fun getBitmap(view: View, urlPath: String): Bitmap {
    return if (!urlPath.isNullOrBlank()) {
      val url = URL(BASE_URL_IMAGE + urlPath)
      BitmapFactory.decodeStream(url.openConnection().getInputStream())
    } else {
      val d =
        view.resources.getDrawable(R.drawable.ic_poster_blank)

      if (d is BitmapDrawable) {
        return d.bitmap
      }

      val bitmap = Bitmap.createBitmap(
        d.intrinsicWidth,
        d.intrinsicHeight,
        Bitmap.Config.ARGB_8888
      )
      val canvas = Canvas(bitmap)
      d.setBounds(0, 0, canvas.width, canvas.height)
      d.draw(canvas)

      bitmap
    }
  }

}