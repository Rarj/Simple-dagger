package grack.dev.moviedagger.ui.movie.search

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

class SearchMovieAdapter(
  val context: Context,
  private var list: List<Result>?,
  private val listenerClick: ClickListener<Result>
) : RecyclerView.Adapter<SearchMovieAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflater = LayoutInflater.from(context)
    val view = ItemCatalogueBinding.inflate(inflater, parent, false)
    return ViewHolder(view)
  }

  override fun getItemCount() = list!!.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(list?.get(position), listenerClick)
  }

  class ViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("CheckResult")
    fun bind(result: Result?, listenerClick: ClickListener<Result>) {
      binding.setVariable(BR.viewModel, result)
      binding.executePendingBindings()

      binding.root.clicks().throttleFirst(DURATION_THROTTLE, TimeUnit.MILLISECONDS)
        .subscribe {
          listenerClick.onItemClick(result)
        }

    }

  }
}