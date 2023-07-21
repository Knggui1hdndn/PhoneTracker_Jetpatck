package com.gps.phonetracker.numberlocator.family.tracklocation

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.graphics.drawable.PaintDrawable
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


class LiveLocation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                GGmap()
            }
        }
    }
}

@Composable
fun GGmap() {
    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 11f)
    }
    Box(Modifier.fillMaxSize()) {
        GoogleMap(cameraPositionState = cameraPositionState) {
            Marker(
                state = MarkerState(position =  LatLng(26.66, 139.6)),
                title = "Marker in Sydney",
                icon =getMarkerIconFromDrawable(ContextCompat.getDrawable(LocalContext.current, R.drawable.baseline_sim_card_24)!!)
            )
            Marker(
                state = MarkerState(position = LatLng(35.66, 180.6)),
                title = "Marker in Tokyo"
            )

            MarkerInfoWindow(
             ) { marker ->
                 Column {
                    Text(marker.title ?: "Default Marker Title", color = Color.Red)
                    Text(marker.snippet ?: "Default Marker Snippet", color = Color.Red)
                }
            }
        }
        Button(onClick = {
            cameraPositionState.move(
                CameraUpdateFactory.newLatLngBounds(
                    LatLngBounds(
                        LatLng(26.66, 139.6),
                        LatLng(35.66, 180.6),

                        ),100
                )
            )
        }) {
            Text(text = "Zoom In")
        }
    }
}
private fun getMarkerIconFromDrawable(drawable: Drawable): BitmapDescriptor  {
    val canvas = Canvas()
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    canvas.setBitmap(bitmap)
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}
@Composable
@Preview(showSystemUi = true)
fun live() {
    GGmap()

}