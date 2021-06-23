package com.adammcneilly.composeinterropsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        setContentView(binding.root)
        subscribeToViewModel()

        setupButton()
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
