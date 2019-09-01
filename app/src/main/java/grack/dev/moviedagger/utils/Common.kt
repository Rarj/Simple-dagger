package grack.dev.moviedagger.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.orhanobut.logger.Logger
import grack.dev.moviedagger.AppConstant.BASE_IMAGE_URL

object Common {

  fun log(message: String) {
    Logger.e(message)
  }

  fun setImage(context: View, url: String?, imageView: ImageView) {
    Glide.with(context)
      .load(BASE_IMAGE_URL + url)
      .into(imageView)
  }

}