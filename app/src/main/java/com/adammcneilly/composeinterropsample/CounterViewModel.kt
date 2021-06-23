package com.adammcneilly.composeinterropsample

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * This controls all of the UI behavior for our counter screen.
 *
 * The [viewState] is a [StateFlow] that emits the configuration of our screen, and [counterButtonClicked]
 * is responsible for updating the count of our state.
 */
class CounterViewModel : ViewModel() {
    private val _viewState = MutableStateFlow(
        CounterViewState(0)
    )

    val viewState: StateFlow<CounterViewState> = _viewState

    /**
     * Every time the counter button is clicked, we should increment
     * our count. This will update the UI accordingly.
     */
    fun counterButtonClicked() {
        val currentState = _viewState.value
        val currentCount = currentState.count

        _viewState.value = currentState.copy(
            count = currentCount.inc(),
        )
    }
}
