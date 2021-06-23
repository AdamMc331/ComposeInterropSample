package com.adammcneilly.composeinterropsample

import android.content.Context
import android.util.AttributeSet
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView

/**
 * By inheriting from [AbstractComposeView] we can create an Android framework View that can be
 * populated using Jetpack Compose code. This way we get the benefit of 2021 UI code, but still
 * being able to add this in XML, as seen in activity_counter.xml.
 */
class CounterButtonView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AbstractComposeView(context, attributeSet, defStyleAttr) {

    var onClick: () -> Unit = {}

    /**
     * The [Content] function is only called when the view is first laid out, if we want to change
     * a public property on this view, we need to make sure that we recompose ourselves.
     *
     * To do that, we can override the setter, and call [disposeComposition] when we need to.
     */
    var buttonText: String = ""
        set(value) {
            field = value

            disposeComposition()
        }

    @Composable
    override fun Content() {
        MaterialTheme {
            Button(onClick = this.onClick) {
                Text(buttonText)
            }
        }
    }
}
