package grack.dev.moviedagger.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private var margin: Int) :
  RecyclerView.ItemDecoration() {
  override fun getItemOffsets(
    outRect: Rect, view: View,
    parent: RecyclerView, state: RecyclerView.State
  ) {
    with(outRect) {
      if (parent.getChildAdapterPosition(view) == 0) {
        top = margin
      }
      left = margin
      right = margin
      bottom = margin
    }
  }
}