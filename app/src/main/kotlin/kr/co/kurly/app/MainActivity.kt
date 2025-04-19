package kr.co.kurly.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import kr.co.kurly.app.ui.theme.BaseTheme
import kr.co.kurly.feature.home.HomeScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      BaseTheme {
        Scaffold { innerPadding ->
          CompositionLocalProvider {
            HomeScreen(
              modifier = Modifier.padding(innerPadding)
            )
          }
        }
      }
    }
  }
}
