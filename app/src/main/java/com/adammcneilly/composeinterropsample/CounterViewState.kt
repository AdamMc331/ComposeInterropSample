package com.adammcneilly.composeinterropsample

/**
 * Defining the view state of our counter screen - by leveraging a data class like this, we can
 * have a state that will work regardless of using compose or existing UI framework.
 */
data class CounterViewState(
    val count: Int,
)
