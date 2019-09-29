package grack.dev.moviedagger.ui.caster

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.jakewharton.rxbinding3.view.clicks
import dagger.android.AndroidInjection
import grack.dev.moviedagger.AppConstant.INTENT_KEY
import grack.dev.moviedagger.R
import grack.dev.moviedagger.data.repository.models.casterlist.Cast
import grack.dev.moviedagger.databinding.ActivityCasterBinding
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CasterActivity : AppCompatActivity() {

  @Inject
  internal lateinit var viewModelFactory: ViewModelProvider.Factory

  private lateinit var binding: ActivityCasterBinding
  private lateinit var viewModel: CasterViewModel
  private lateinit var adapter: CasterAdapter
  private lateinit var cast: Cast
  private var compositeDisposable = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidInjection.inject(this)

    viewModel = ViewModelProviders.of(this, viewModelFactory).get(CasterViewModel::class.java)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_caster)
    binding.viewModel = viewModel
    binding.lifecycleOwner = this

    cast = Gson().fromJson(intent.getStringExtra(INTENT_KEY), Cast::class.java)

    compositeDisposable.add(viewModel.loadCasterDetail(cast.id).subscribe {
      if (!viewModel.result.value?.homepage.isNullOrEmpty()) {
        binding.buttonHomepage.visibility = VISIBLE
      } else {
        binding.buttonHomepage.visibility = INVISIBLE
      }

      if (!viewModel.result.value?.imdbId.isNullOrEmpty()) {
        binding.buttonImdb.visibility = VISIBLE
      } else {
        binding.buttonImdb.visibility = INVISIBLE
      }

      adapter = CasterAdapter(viewModel.result.value!!.alsoKnownAs)

      binding.recyclerKnownAs.layoutManager = LinearLayoutManager(this)
      binding.recyclerKnownAs.adapter = adapter
      adapter.notifyDataSetChanged()

      viewModel.loadingVisibility.value = 8
      viewModel.showDetail.value = 0
    })

    compositeDisposable.add(
      binding.buttonHomepage.clicks().throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe {
          toast("homepage")
        }
    )

    compositeDisposable.add(
      binding.buttonImdb.clicks().throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe {
          toast("imdb clicked")
        }
    )

  }

  override fun onDestroy() {
    super.onDestroy()
    compositeDisposable.clear()
  }
}
