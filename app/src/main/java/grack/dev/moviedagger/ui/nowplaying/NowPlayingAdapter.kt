package grack.dev.moviedagger.ui.nowplaying

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import grack.dev.moviedagger.BR
import grack.dev.moviedagger.data.repository.nowplaying.model.Result
import grack.dev.moviedagger.databinding.ItemNowPlayingBinding

class NowPlayingAdapter(var list: MutableList<Result>) :
  RecyclerView.Adapter<NowPlayingAdapter.CustomViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val dataBinding = ItemNowPlayingBinding.inflate(inflater, parent, false)
    return CustomViewHolder(dataBinding)
  }

  override fun getItemCount(): Int {
    return list.size
  }

  override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
    holder.bind(list[position])
  }

  fun updateCountries(newList: List<Result>) {
    list.clear()
    list.addAll(newList)
    notifyDataSetChanged()
  }

  class CustomViewHolder(private val dataBinding: ItemNowPlayingBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {

    fun bind(data: Result) {
      Glide.with(dataBinding.root)
        .load("http://image.tmdb.org/t/p/original/" + data.backdropPath)
        .into(dataBinding.imagePoster)

      dataBinding.setVariable(BR.viewModel, data)
      dataBinding.executePendingBindings()
    }
  }

}