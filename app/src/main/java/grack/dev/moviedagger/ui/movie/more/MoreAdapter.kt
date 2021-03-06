package grack.dev.moviedagger.ui.movie.more

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import grack.dev.moviedagger.AppConstant.DURATION_THROTTLE
import grack.dev.moviedagger.BR
import grack.dev.moviedagger.data.repository.models.general.Result
import grack.dev.moviedagger.databinding.ItemCatalogueBinding
import grack.dev.moviedagger.utils.ClickListener
import java.util.concurrent.TimeUnit

class MoreAdapter(
  var context: Context,
  var list: List<Result>?,
  var listener: ClickListener<Result>
) : RecyclerView.Adapter<MoreAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflate = LayoutInflater.from(context)
    val view = ItemCatalogueBinding.inflate(inflate, parent, false)

    return ViewHolder(view)
  }

  override fun getItemCount(): Int = list?.size!!

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val result = list?.get(position)
    holder.bind(result, listener)
  }

  class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("CheckResult")
    fun bind(result: Result?, listener: ClickListener<Result>) {
      binding.setVariable(BR.viewModel, result)
      binding.executePendingBindings()

      binding.root.clicks()
        .throttleFirst(DURATION_THROTTLE, TimeUnit.MILLISECONDS).subscribe {
          listener.onItemClick(result)
        }
    }
  }

}