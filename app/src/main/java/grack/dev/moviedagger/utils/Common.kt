package grack.dev.moviedagger.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
    Glide.with(context)
      .asBitmap()
      .load(BASE_URL_IMAGE + url)
      .into(imageView)
  }

  fun setImageCircle(context: View, url: String?, imageView: AppCompatImageView) {
    if (url.isNullOrEmpty()) {
      Glide.with(context)
        .load(R.drawable.person)
        .into(imageView)
    } else {
      Glide.with(context)
        .load(BASE_URL_IMAGE + url)
        .into(imageView)
    }
  }

  fun getBitmap(urlPath: String): Bitmap {
    val url = URL(BASE_URL_IMAGE + urlPath)
    return BitmapFactory.decodeStream(url.openConnection().getInputStream())
  }

}