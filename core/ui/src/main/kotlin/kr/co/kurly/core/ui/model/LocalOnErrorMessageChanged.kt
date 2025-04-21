package kr.co.kurly.core.ui.model

import androidx.annotation.Keep
import androidx.compose.runtime.compositionLocalOf
import kr.co.kurly.core.ui.CenterErrorDialogMessage

@Keep
val LocalOnErrorMessageChanged = compositionLocalOf<(CenterErrorDialogMessage) -> Unit> { {} }
@Keep
val LocalOnLoadingStateChanged = compositionLocalOf<(isLoading: Boolean) -> Unit> { {} }