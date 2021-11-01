package com.example.presentation.dialog

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.presentation.R
import com.example.presentation.core.intent.openSystemSetting

@Composable
fun SystemSettingDialog(
    onDismissRequest: () -> Unit,
) {
    val context = LocalContext.current
    AlertDialog(
        title = {
            Text(text = context.getString(R.string.dialog_system_setting_title))
        },
        confirmButton = {
            TextButton(
                onClick = {
                    openSystemSetting(context)
                    onDismissRequest()
                },
            ) {
                Text(context.getString(R.string.dialog_system_setting_ok))
            }
        },
        onDismissRequest = onDismissRequest,
        dismissButton = null
    )
}
