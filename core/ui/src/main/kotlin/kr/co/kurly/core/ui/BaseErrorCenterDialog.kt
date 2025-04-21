package kr.co.kurly.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

data class CenterErrorDialogMessage(
  val errorCode: Int,
  val titleMessage: String,
  val contentMessage: String,
  val confirmButtonMessage: String
)

@Composable
fun BaseErrorCenterDialog(
  centerErrorDialogMessage: CenterErrorDialogMessage,
  onDismissDialog: () -> Unit = { },
  onClickedConfirm: () -> Unit = { }
) {
  Dialog(
    onDismissRequest = onDismissDialog,
    properties = DialogProperties(
      dismissOnBackPress = true,
      dismissOnClickOutside = true,
    )
  ) {
    Box {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .background(
            color = Color.White,
            shape = RoundedCornerShape(10.dp)
          )
          .padding(10.dp)
          .padding(bottom = 70.dp)
          .verticalScroll(rememberScrollState())
      ) {
        if (centerErrorDialogMessage.titleMessage.isNotEmpty()) {
          Text(
            modifier = Modifier
              .align(Alignment.CenterHorizontally),
            text = centerErrorDialogMessage.titleMessage,
            fontWeight = FontWeight.Bold
          )
        }

        if (centerErrorDialogMessage.contentMessage.isNotEmpty()) {
          Text(
            modifier = Modifier
              .padding(top = 10.dp)
              .fillMaxSize()
              .align(Alignment.CenterHorizontally),
            text = centerErrorDialogMessage.contentMessage,
            textAlign = TextAlign.Center
          )
        }
      }

      Button(
        modifier = Modifier
          .align(Alignment.BottomCenter),
        onClick = onClickedConfirm,
        colors = ButtonDefaults.buttonColors().copy(
          containerColor = Color.Black,
          contentColor = Color.White
        )
      ) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
        ) {
          Text(
            modifier = Modifier
              .align(Alignment.Center),
            text = centerErrorDialogMessage.confirmButtonMessage
          )
        }
      }
    }
  }
}