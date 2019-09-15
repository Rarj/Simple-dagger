package grack.dev.moviedagger.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import dagger.android.support.AndroidSupportInjection
import grack.dev.moviedagger.AppConstant.INTENT_KEY
import grack.dev.moviedagger.R
import grack.dev.moviedagger.data.repository.nowplaying.model.Result
import grack.dev.moviedagger.databinding.ActivityDetailBinding
import grack.dev.moviedagger.ui.trailer.TrailerActivity
import javax.inject.Inject

class DetailActivity : BottomSheetDialogFragment() {

  @Inject
  internal lateinit var viewModelFactory: ViewModelProvider.Factory

  private lateinit var binding: ActivityDetailBinding
  private lateinit var viewModel: DetailViewModel
  private lateinit var data: Result

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    AndroidSupportInjection.inject(this)

    viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)
    binding = DataBindingUtil.inflate(
      inflater,
      R.layout.activity_detail,
      container,
      false
    )
    binding.viewModel = viewModel
    binding.lifecycleOwner = this

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val bundle = this.arguments
    if (bundle != null) {
      data = Gson().fromJson(bundle.getString(INTENT_KEY), Result::class.java)
      viewModel.result.value = data
    }

    viewModel.loadTrailer(viewModel.result.value?.id)

    listener()
  }

  private fun listener() {
    binding.buttonBack.setOnClickListener { dismiss() }

    binding.buttonTrailer.setOnClickListener {
      val intent = Intent(context, TrailerActivity::class.java)
      intent.putExtra(INTENT_KEY, viewModel.itemTrailer.value?.get(0)?.key)
      startActivity(intent)
    }
  }

  override fun onStart() {
    super.onStart()
    val bottomSheet = dialog?.findViewById<FrameLayout>(R.id.design_bottom_sheet)
    bottomSheet?.layoutParams?.height = ViewGroup.LayoutParams.MATCH_PARENT
  }

}