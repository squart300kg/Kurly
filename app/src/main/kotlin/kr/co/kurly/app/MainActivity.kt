package kr.co.kurly.app

import android.os.Bundle
import androidx.compose.ui.Modifier
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.co.kurly.app.ui.navigation.BaseNavigationBarWithItems
import kr.co.kurly.app.ui.theme.BaseTheme
import kr.co.kurly.feature.first.FIRST_BASE_ROUTE
import kr.co.kurly.feature.first.firstScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      BaseTheme {
        val navHostController = rememberNavController()

        Scaffold(
          bottomBar = {
            BaseNavigationBarWithItems(navHostController)
          }
        ) { innerPadding ->
          CompositionLocalProvider() {
            NavHost(
              modifier = Modifier.padding(innerPadding),
              navController = navHostController,
              startDestination = FIRST_BASE_ROUTE
            ) {
              firstScreen()

            }
          }
        }
      }
    }
  }
}
