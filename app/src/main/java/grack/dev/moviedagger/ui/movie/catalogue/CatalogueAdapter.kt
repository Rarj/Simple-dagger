package grack.dev.moviedagger.ui.movie.catalogue

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.intrusoft.sectionedrecyclerview.SectionRecyclerViewAdapter
import com.jakewharton.rxbinding3.view.clicks
import grack.dev.moviedagger.AppConstant.DURATION_THROTTLE
import grack.dev.moviedagger.BR
import grack.dev.moviedagger.data.repository.models.general.Result
import grack.dev.moviedagger.databinding.ItemCatalogueBinding
import grack.dev.moviedagger.databinding.ItemSectionHeaderBinding
import grack.dev.moviedagger.ui.movie.catalogue.model.Child
import grack.dev.moviedagger.ui.movie.catalogue.model.SectionItem
import grack.dev.moviedagger.utils.ClickListener
import java.util.concurrent.TimeUnit

class CatalogueAdapter(
  context: Context?,
  sectionItemList: MutableList<SectionItem>?,
  private val clickListener: ClickListener<Result>,
  private val headerClickListener: ClickListener<String>
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
    sectionViewHolder.bind(sectionItem?.title, headerClickListener)
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
    childViewHolder?.bind(child, clickListener)
  }

  class SectionViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("CheckResult")
    fun bind(header: String?, clickListener: ClickListener<String>) {
      binding.setVariable(BR.sectionItem, header)
      binding.executePendingBindings()
      binding.root.clicks().throttleFirst(DURATION_THROTTLE, TimeUnit.MILLISECONDS)
        .subscribe {
          clickListener.onItemClick(header)
        }
    }
  }

  class ChildViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("CheckResult")
    fun bind(child: Child?, clickListener: ClickListener<Result>) {
      binding.setVariable(BR.viewModel, child?.result)
      binding.executePendingBindings()

      binding.root.clicks().throttleFirst(DURATION_THROTTLE, TimeUnit.MILLISECONDS)
        .subscribe {
          clickListener.onItemClick(child?.result)
        }
    }
  }

}