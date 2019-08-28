package grack.dev.moviedagger.ui.nowplaying

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import grack.dev.moviedagger.api.nowplaying.model.Result
import grack.dev.moviedagger.databinding.ItemNowPlayingBinding
import kotlinx.android.synthetic.main.item_now_playing.view.*

class NowPlayingAdapter(var list: ArrayList<Result>) :
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

    class CustomViewHolder(val dataBinding: ViewDataBinding) :
            RecyclerView.ViewHolder(dataBinding.root) {
        private val title = dataBinding.root.text_title
        private val description = dataBinding.root.text_description
        private val image = dataBinding.root.image_poster

        fun bind(data: Result) {
            Glide.with(dataBinding.root).load("http://image.tmdb.org/t/p/original/" + data.backdropPath).into(image)
            title.text = data.title
            description.text = data.overview
        }
    }

}