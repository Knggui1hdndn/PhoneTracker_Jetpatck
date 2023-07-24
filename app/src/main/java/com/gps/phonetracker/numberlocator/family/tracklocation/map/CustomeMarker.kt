package com.gps.phonetracker.numberlocator.family.tracklocation.map

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gps.phonetracker.numberlocator.family.tracklocation.R
import com.smarttoolfactory.screenshot.ScreenshotBox
import com.smarttoolfactory.screenshot.ScreenshotState
import com.smarttoolfactory.screenshot.rememberScreenshotState
import dev.shreyaspatil.capturable.Capturable
import dev.shreyaspatil.capturable.controller.CaptureController
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.withContext

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomMarker(

    pin: String,
    avatar: String,
    speed: String,
    call: (ImageBitmap) -> Unit
) {
    val screenshotState = rememberCaptureController()
    var x = 0
    Log.d("sssssss//////","sfasfsdaf")
    LaunchedEffect(x){
        withContext(Dispatchers.Main) {
            screenshotState.capture()
        }
    }
    Capturable(controller = screenshotState,onCaptured={b,t->
        if (b!=null){
            call(b)
            Log.d("sssssss//////221","sfasfsdaf")

        }
        Log.d("./////dsgfd/////2", t.toString())

    }) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(Color.Transparent)
        ) {
            Box(
                modifier = Modifier.background(Color.Transparent)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.map),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .width(60.dp)
                        .height(60.dp)
                        .background(Color.Transparent)
                )
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(avatar)
                        .allowHardware(false)
                        .build(),
                    contentDescription = "This is an example image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .width(60.dp)
                        .height(40.dp)
                        .padding(3.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    onSuccess = {
                        x=1
                    }
                )
                Column(
                    modifier = Modifier
                        .padding(top = 20.dp, start = 5.dp)
                        .width(30.dp)
                        .height(30.dp)
                        .padding(2.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color.Black),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = speed, color = Color.White, fontSize = 8.sp)
                }
            }
            Box(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .shadow(2.dp, spotColor = Color.Gray, shape = RoundedCornerShape(15.dp))
                    .padding(2.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(Color.White)
            ) {
                Row() {
                    Icon(
                        Icons.Default.Phone, contentDescription = null, modifier = Modifier
                            .width(15.dp)
                            .height(15.dp)
                    )
                    Text(text = pin, fontSize = 10.sp)
                }
            }

        }

    }
}

