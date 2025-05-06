package com.example.mz_focusnews

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.content.ContextCompat

fun checkAndRequestPermission(
    context: Context,
    permissions: Array<String>,
    launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>
) {
    Log.d("Permission", "checkAndRequestPermission called:: ${permissions.map { it }}")

    if (permissions.all {
            ContextCompat.checkSelfPermission(
                context,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }) {
        Log.d("Permission", "권한이 이미 존재합니다")
    } else {
        launcher.launch(permissions)
        Log.d("Permission", "권한을 요청하였습니다")
    }
}
