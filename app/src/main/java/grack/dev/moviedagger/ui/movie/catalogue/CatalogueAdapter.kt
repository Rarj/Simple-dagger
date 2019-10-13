package grack.dev.moviedagger.ui.movie.catalogue

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.intrusoft.sectionedrecyclerview.SectionRecyclerViewAdapter
import grack.dev.moviedagger.BR
import grack.dev.moviedagger.databinding.ItemCatalogueBinding
import grack.dev.moviedagger.databinding.ItemSectionHeaderBinding
import grack.dev.moviedagger.ui.movie.catalogue.model.Child
import grack.dev.moviedagger.ui.movie.catalogue.model.SectionItem

class CatalogueAdapter(
  val context: Context?,
  sectionItemList: MutableList<SectionItem>?
) :
  SectionRecyclerViewAdapter<
        SectionItem,
        Child,
        CatalogueAdapter.SectionViewHolder, CatalogueAdapter.ChildViewHolder>(
    context,
    sectionItemList
  ) {

  override fun onCreateSectionViewHolder(viewGroup: ViewGroup, viewType: Int): SectionViewHolder {
    val inflater = LayoutInflater.from(viewGroup.context)
    val dataBinding = ItemSectionHeaderBinding.inflate(inflater, viewGroup, false)
    return SectionViewHolder(dataBinding)
  }

  override fun onBindSectionViewHolder(
    sectionViewHolder: SectionViewHolder,
    position: Int,
    sectionItem: SectionItem?
  ) {
    sectionViewHolder.bind(sectionItem?.title)
  }

  override fun onCreateChildViewHolder(viewGroup: ViewGroup, viewType: Int): ChildViewHolder {
    val inflater = LayoutInflater.from(viewGroup.context)
    val dataBinding = ItemCatalogueBinding.inflate(inflater, viewGroup, false)
    return ChildViewHolder(dataBinding)
  }

  override fun onBindChildViewHolder(
    childViewHolder: ChildViewHolder?,
    headerPosition: Int,
    childPosition: Int,
    child: Child?
  ) {
    childViewHolder?.bind(child)
  }

  class SectionViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(header: String?) {
      binding.setVariable(BR.sectionItem, header)
      binding.executePendingBindings()
    }
  }

  class ChildViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(child: Child?) {
      binding.setVariable(BR.viewModel, child?.result)
      binding.executePendingBindings()
    }
  }

}