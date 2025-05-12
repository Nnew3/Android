package com.example.mz_focusnews

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mz_focusnews.core.theme.Bg_Blue
import com.example.mz_focusnews.core.theme.MZ_FocusNews_Theme
import com.example.mz_focusnews.main.MainScreen
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) { // 권한 허용
            checkLocationService()
        } else { // 권한 거부
            Toast.makeText(this, "위치 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        checkAndRequestLocationPermission()

        enableEdgeToEdge()
        setContent {
            MZ_FocusNews_Theme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Bg_Blue)
                ) {
                    MainScreen()
                }
            }
        }
    }

    private fun checkAndRequestLocationPermission() {
        Log.d("Location", "checkAndRequestLocationPermission")
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            checkLocationService()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun checkLocationService() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "위치 서비스가 꺼져 있습니다. 설정에서 활성화하세요.", Toast.LENGTH_LONG)
                .show()

            // 위치 설정 화면으로 이동
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        } else {
            getLastLocation()
        }
    }

    private fun getLastLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        fusedLocationClient.lastLocation.addOnCompleteListener { task: Task<Location> ->
            Log.d("Location", "task=${task.isSuccessful}  ${task.result}")
            if (task.isSuccessful && task.result != null) {
                val location = task.result
                val lat = location.latitude
                val lon = location.longitude

                Log.d("Location", "위도: $lat, 경도: $lon")
                val prefs = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                prefs.edit()
                    .putFloat("lat", lat.toFloat())
                    .putFloat("lon", lon.toFloat())
                    .apply()
            } else {
                Log.d("Location", "위치를 가져올 수 없습니다.")
            }
        }
    }
}
