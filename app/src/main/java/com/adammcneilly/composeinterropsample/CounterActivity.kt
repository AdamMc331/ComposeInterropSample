package com.adammcneilly.composeinterropsample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.adammcneilly.composeinterropsample.databinding.ActivityCounterBinding
import kotlinx.coroutines.flow.collect

class CounterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCounterBinding
    private lateinit var viewModel: CounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCounterBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(CounterViewModel::class.java)

        setContent {
            val state = viewModel.viewState.collectAsState()

            Surface {
                CounterScreen(
                    viewState = state.value,
                    onIncrementButtonClicked = viewModel::counterButtonClicked
                )
            }
        }
//        setContentView(binding.root)
//        subscribeToViewModel()
//
//        setupButton()
    }

    private fun setupButton() {
        binding.counterButton.buttonText = getString(R.string.increment)
        binding.counterButton.onClick = viewModel::counterButtonClicked
    }

    private fun subscribeToViewModel() {
        lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect { viewState ->
                processViewState(viewState)
            }
        }
    }

    private fun processViewState(viewState: CounterViewState) {
        binding.counterText.text = resources.getString(R.string.counter_value, viewState.count)
    }
}
