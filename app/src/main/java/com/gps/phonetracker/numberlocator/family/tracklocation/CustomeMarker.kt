package com.gps.phonetracker.numberlocator.family.tracklocation

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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.smarttoolfactory.screenshot.ScreenshotBox
import com.smarttoolfactory.screenshot.rememberScreenshotState
import dev.shreyaspatil.capturable.Capturable
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.launchIn

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomMarker(pin: String, avatar: String, speed: String, call: (ImageBitmap) -> Unit) {
    val screenshotState = rememberScreenshotState()
    Column() {
        ScreenshotBox(screenshotState = screenshotState) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
            ) {
                Box(modifier = Modifier) {
                    Image(
                        painter = painterResource(id = R.drawable.map),
                        contentDescription = null,
                        modifier = Modifier
                            .width(60.dp)
                            .height(60.dp)
                    )
                    AsyncImage(
                        model = avatar,
                        contentDescription = "This is an example image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(60.dp)
                            .height(40.dp)
                            .padding(3.dp)
                            .clip(RoundedCornerShape(3.dp))
                    )
                    Column(
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .padding(2.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(Color.Black)
                    ) {
                        Text(text = "36 m", color = Color.White, fontSize = 8.sp)
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .shadow(2.dp, spotColor = Color.Gray, shape = RoundedCornerShape(15.dp))
                        .padding(2.dp)
                        .clip(RoundedCornerShape(3.dp))
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
            screenshotState.imageBitmap?.let {
                call(it)
                screenshotState.liveScreenshotFlow.cancellable()
                Log.d("sssssssssssss","sssssssssssss")
            }
        }
        LaunchedEffect(Unit) {
            screenshotState.liveScreenshotFlow.launchIn(this)
        }
    }
}

