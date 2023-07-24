package com.gps.phonetracker.numberlocator.family.tracklocation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.rememberCameraPositionState
import com.gps.phonetracker.numberlocator.family.tracklocation.map.CustomMarker
import com.gps.phonetracker.numberlocator.family.tracklocation.model.Users
import com.gps.phonetracker.numberlocator.family.tracklocation.viewModel.FireBaseViewModel

//https://chetangupta.net/compose-bitmap/
class LiveLocation : AppCompatActivity() {
    @SuppressLint("MutableCollectionMutableState")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val firestore by viewModels<FireBaseViewModel>()
        firestore.onValueChangeListens()
        val user = Users(
            12311, "Nguyễn DUy Khiêm",
            "https://anhdepfree.com/wp-content/uploads/2022/12/hinh-nen-may-tinh-4k-chill_22343462136-1080x608.jpg",
            "80%",
            "2280 km/h",
            true,
            37.66, 139.6
        )
        //  firestore.addUserFireBase(user)
        val usersState = firestore.onChange

        setContent {
            var bitmap = remember {
                mutableStateMapOf<Int, Pair<ImageBitmap?, Users>>()
            }
            var ii = remember {
                mutableStateOf<String>("")
            }
            var c = remember {
                mutableStateOf<String>("")
            }
            usersState.collectAsState().value.forEachIndexed { i, user ->
                ii.value=i
            }
            CustomMarker(
                pin = user.pin,
                avatar = user.avatar,
                speed = user.speed
            ) { it ->
                bitmap[i] = Pair(it, user)
                Log.d("./////dsgfd/////2", user.pin)
            }
            GGmap(bitmap)
        }
    }
}


@OptIn(MapsComposeExperimentalApi::class)
@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GGmap(bitmap: SnapshotStateMap<Int, Pair<ImageBitmap?, Users>>) {
    Log.d("sssssssssssaasadscssss", bitmap.toString())
    var map: GoogleMap? by remember { mutableStateOf(null) }
    val singapore = LatLng(35.66, 139.6)
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 11f)
    }
    GoogleMap(cameraPositionState = cameraPositionState) {
        MapEffect {
            map = it
        }
    }

    bitmap.entries.forEach { t ->
        map?.addMarker(
            MarkerOptions().position(LatLng(t.value.second.latitude, t.value.second.longitude))
                .title(t.value.second.title).icon(
                    BitmapDescriptorFactory.fromBitmap(t.value.first?.asAndroidBitmap()!!)
                )
        )
    }

}

