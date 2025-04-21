package kr.co.kurly.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import kr.co.kurly.app.ui.theme.BaseTheme
import kr.co.kurly.core.ui.BaseErrorCenterDialog
import kr.co.kurly.core.ui.BaseLoadingProgress
import kr.co.kurly.core.ui.CenterErrorDialogMessage
import kr.co.kurly.core.ui.model.LocalOnErrorMessageChanged
import kr.co.kurly.core.ui.model.LocalOnLoadingStateChanged
import kr.co.kurly.feature.home.HomeScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      BaseTheme {
        Scaffold { innerPadding ->
          var errorMessageState: CenterErrorDialogMessage? by remember { mutableStateOf(null) }
          var isLoading by remember { mutableStateOf(false) }
          CompositionLocalProvider(
            LocalOnErrorMessageChanged provides { errorMessageState = it },
            LocalOnLoadingStateChanged provides { isLoading = it }
          ) {
            HomeScreen(
              modifier = Modifier.padding(innerPadding)
            )
          }

          errorMessageState?.let {
            BaseErrorCenterDialog(
              centerErrorDialogMessage = it,
              onDismissDialog = { errorMessageState = null },
              onClickedConfirm = { errorMessageState = null }
            )
          }

          BaseLoadingProgress(
            isLoading = isLoading
          )
        }
      }
    }
  }
}
