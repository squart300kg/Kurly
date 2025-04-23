package kr.co.kurly.core.ui.model

import androidx.compose.runtime.compositionLocalOf
import kr.co.kurly.core.ui.CenterErrorDialogMessage

val LocalOnErrorMessageChanged = compositionLocalOf<(CenterErrorDialogMessage) -> Unit> { {} }
val LocalOnLoadingStateChanged = compositionLocalOf<(isLoading: Boolean) -> Unit> { {} }