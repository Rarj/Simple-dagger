package grack.dev.moviedagger.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import grack.dev.moviedagger.BR
import grack.dev.moviedagger.data.repository.trailer.model.cast.Cast
import grack.dev.moviedagger.databinding.ItemCastBinding

class CasterAdapter(var list: MutableList<Cast>) :
  RecyclerView.Adapter<CasterAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val view = ItemCastBinding.inflate(inflater, parent, false)

    return ViewHolder(view)
  }

  override fun getItemCount() = list.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(list[position])
  }

  fun populateCasts(casts: List<Cast>) {
    list.clear()
    list.addAll(casts)
    notifyDataSetChanged()
  }

  inner class ViewHolder(private val binding: ItemCastBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(cast: Cast) {
      binding.setVariable(BR.viewModel, cast)
      binding.executePendingBindings()
    }

  }

}