package digital.studioweb.selfhub_app.ui.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun DualDrawerLayout(
    showLeftDrawer: Boolean,
    showRightDrawer: Boolean,
    onCloseRightDrawer: () -> Unit,
    rightDrawerContent: @Composable (onCloseDrawer: () -> Unit) -> Unit,
    content: @Composable () -> Unit
) {
    val isAnyDrawerOpen = showLeftDrawer || showRightDrawer

    Box(modifier = Modifier.fillMaxSize()) {
        content()

        if (isAnyDrawerOpen) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .pointerInput(showLeftDrawer, showRightDrawer) {
                        detectTapGestures {
                            if (showRightDrawer) onCloseRightDrawer()
                        }
                    }
            )
        }

        AnimatedVisibility(
            visible = showRightDrawer,
            enter = slideInHorizontally(initialOffsetX = { it }) + fadeIn(),
            exit = slideOutHorizontally(targetOffsetX = { it }) + fadeOut(),
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .fillMaxHeight()
                    .background(Color.White)
                    .shadow(8.dp)
                    .pointerInput(Unit) {}
            ) {
                rightDrawerContent(onCloseRightDrawer)
            }
        }
    }
}
