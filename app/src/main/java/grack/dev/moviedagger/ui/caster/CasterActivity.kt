package grack.dev.moviedagger.ui.caster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import dagger.android.AndroidInjection
import grack.dev.moviedagger.AppConstant.INTENT_KEY
import grack.dev.moviedagger.R
import grack.dev.moviedagger.data.repository.models.casterlist.Cast
import grack.dev.moviedagger.databinding.ActivityCasterBinding
import javax.inject.Inject

class CasterActivity : AppCompatActivity() {

  @Inject
  internal lateinit var viewModelFactory: ViewModelProvider.Factory

  private lateinit var binding: ActivityCasterBinding
  private lateinit var viewModel: CasterViewModel
  private lateinit var cast: Cast

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidInjection.inject(this)

    viewModel = ViewModelProviders.of(this, viewModelFactory).get(CasterViewModel::class.java)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_caster)
    binding.viewModel = viewModel
    binding.lifecycleOwner = this

    cast = Gson().fromJson(intent.getStringExtra(INTENT_KEY), Cast::class.java)

    viewModel.loadCasterDetail(cast.id)

  }
}
