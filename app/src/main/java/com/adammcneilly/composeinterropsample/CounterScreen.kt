package com.adammcneilly.composeinterropsample

import android.content.res.Configuration
import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun CounterScreen(
    viewState: CounterViewState,
    onIncrementButtonClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // We use the AndroidView composable to use a `View` class inside of our
        // jetpack compose code.
        // The factory parameter is used to create the view.
        // The modifier parameter is a standard Compose modifier to define any customization
        // on this composable.
        // The update parameter is a lambda that is called when the view is inflated,
        // or a state value has changed.
        AndroidView(
            factory = { context ->
                TextView(context)
            },
            modifier = Modifier,
            update = { view ->
                view.text = view.context.getString(
                    R.string.counter_value,
                    viewState.count,
                )
            },
        )

        Button(onClick = onIncrementButtonClicked) {
            Text(stringResource(id = R.string.increment))
        }
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun CounterScreenPreview() {
    MaterialTheme {
        Surface {
            CounterScreen(
                viewState = CounterViewState(0),
                onIncrementButtonClicked = {},
            )
        }
    }
}
