package grack.dev.moviedagger.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.orhanobut.logger.Logger
import grack.dev.moviedagger.AppConstant.BASE_IMAGE_URL
import java.net.URL

object Common {

  fun log(message: String) {
    Logger.e(message)
  }

  fun setImage(context: View, url: String?, imageView: ImageView) {
    Glide.with(context)
      .asBitmap()
      .load(BASE_IMAGE_URL + url)
      .into(imageView)
  }

  fun getBitmap(urlPath: String): Bitmap {
    val url = URL(BASE_IMAGE_URL + urlPath)
    return BitmapFactory.decodeStream(url.openConnection().getInputStream())
  }

}