package grack.dev.moviedagger.ui.caster

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import grack.dev.moviedagger.AppConstant
import grack.dev.moviedagger.BR
import grack.dev.moviedagger.data.repository.models.casterlist.Cast
import grack.dev.moviedagger.databinding.ItemCastBinding
import grack.dev.moviedagger.utils.ClickListener
import java.util.concurrent.TimeUnit

class CasterAdapter(var list: MutableList<Cast>,
                    var listener: ClickListener<Cast>) :
  RecyclerView.Adapter<CasterAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val view = ItemCastBinding.inflate(inflater, parent, false)

    return ViewHolder(view)
  }

  override fun getItemCount() = list.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(list[position], listener)
  }

  fun populateCasts(casts: List<Cast>) {
    list.clear()
    list.addAll(casts)
    notifyDataSetChanged()
  }

  inner class ViewHolder(private val binding: ItemCastBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("CheckResult")
    fun bind(cast: Cast, listener: ClickListener<Cast>) {

      binding.root.clicks()
              .throttleFirst(AppConstant.DURATION_THROTTLE, TimeUnit.MILLISECONDS)
              .subscribe {
                listener.onItemClick(cast)
              }

      binding.setVariable(BR.viewModel, cast)
      binding.executePendingBindings()
    }

  }

}