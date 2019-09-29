package grack.dev.moviedagger.ui.caster

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import grack.dev.moviedagger.AppConstant
import grack.dev.moviedagger.BR
import grack.dev.moviedagger.databinding.ItemKnowsAsBinding
import grack.dev.moviedagger.utils.ClickListener
import java.util.concurrent.TimeUnit

class CasterAdapter(
  val list: List<String>,
  val listener: ClickListener<String>
) : RecyclerView.Adapter<CasterAdapter.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val view = ItemKnowsAsBinding.inflate(inflater, parent, false)

    return ViewHolder(view)
  }

  override fun getItemCount() = list.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(list[position], listener)
  }

  inner class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("CheckResult")
    fun bind(response: String, listener: ClickListener<String>) {
      binding.root.clicks()
        .throttleFirst(AppConstant.DURATION_THROTTLE, TimeUnit.MILLISECONDS)
        .subscribe {
          listener.onItemClick(response)
        }

      binding.setVariable(BR.viewModel, response)
      binding.executePendingBindings()
    }

  }

}