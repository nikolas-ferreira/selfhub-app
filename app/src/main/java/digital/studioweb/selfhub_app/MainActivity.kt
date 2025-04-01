package digital.studioweb.selfhub_app

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import dagger.hilt.android.AndroidEntryPoint
import digital.studioweb.selfhub_app.ui.home.HomeScreen
import digital.studioweb.selfhub_app.ui.theme.SelfHubAppTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("WrongConstant")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.let { controller ->
            controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        enableEdgeToEdge()
        setContent {
            SelfHubAppTheme {
                HomeScreen()
            }
        }
    }
}