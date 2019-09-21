package grack.dev.moviedagger.ui.nowplaying

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import grack.dev.moviedagger.AppConstant.DURATION_THROTTLE
import grack.dev.moviedagger.BR
import grack.dev.moviedagger.data.repository.models.general.Result
import grack.dev.moviedagger.databinding.ItemNowPlayingBinding
import grack.dev.moviedagger.utils.ClickListener
import java.util.concurrent.TimeUnit

class NowPlayingAdapter(
  var list: MutableList<Result>,
  var listener: ClickListener<Result>
) :
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
    holder.bind(list[position], listener)
  }

  fun updateCountries(newList: List<Result>) {
    list.clear()
    list.addAll(newList)
    notifyDataSetChanged()
  }

  class CustomViewHolder(private val dataBinding: ItemNowPlayingBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {

    @SuppressLint("CheckResult")
    fun bind(data: Result, listener: ClickListener<Result>) {

      dataBinding.root.clicks()
        .throttleFirst(DURATION_THROTTLE, TimeUnit.MILLISECONDS)
        .subscribe {
          listener.onItemClick(data)
        }

      dataBinding.setVariable(BR.viewModel, data)
      dataBinding.executePendingBindings()
    }
  }

}