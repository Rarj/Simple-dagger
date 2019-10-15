package grack.dev.moviedagger.ui.movie.more

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import grack.dev.moviedagger.BR
import grack.dev.moviedagger.data.repository.models.general.Result
import grack.dev.moviedagger.databinding.ItemCatalogueBinding

class MoreAdapter(
  var context: Context,
  var list: List<Result>?
) : RecyclerView.Adapter<MoreAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflate = LayoutInflater.from(context)
    val view = ItemCatalogueBinding.inflate(inflate, parent, false)

    return ViewHolder(view)
  }

  override fun getItemCount(): Int = list?.size!!

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val result = list?.get(position)
    holder.bind(result)
  }

  class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(result: Result?) {
      binding.setVariable(BR.viewModel, result)
      binding.executePendingBindings()
    }
  }

}