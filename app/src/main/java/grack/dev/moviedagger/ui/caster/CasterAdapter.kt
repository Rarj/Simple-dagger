package grack.dev.moviedagger.ui.caster

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import grack.dev.moviedagger.BR
import grack.dev.moviedagger.R
import grack.dev.moviedagger.databinding.ItemKnowsAsBinding
import kotlinx.android.synthetic.main.item_knows_as.view.*

class CasterAdapter(val list: List<String>) : RecyclerView.Adapter<CasterAdapter.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val view = ItemKnowsAsBinding.inflate(inflater, parent, false)

    return ViewHolder(view)
  }

  override fun getItemCount() = list.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(list[position])

    if (position % 2 == 0) {
      holder.binding.root.text_known_as.setBackgroundResource(R.color.nobel_B3B3B3)
    } else {
      holder.binding.root.text_known_as.setBackgroundResource(R.color.white_FFFFFF)
    }

  }

  inner class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(response: String) {
      binding.setVariable(BR.viewModel, response)
      binding.executePendingBindings()
    }

  }

}