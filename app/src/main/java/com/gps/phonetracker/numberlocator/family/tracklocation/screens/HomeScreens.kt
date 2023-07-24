package com.gps.phonetracker.numberlocator.family.tracklocation.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gps.phonetracker.numberlocator.family.tracklocation.LiveLocation
import com.gps.phonetracker.numberlocator.family.tracklocation.model.Function
import com.gps.phonetracker.numberlocator.family.tracklocation.NumberLocator
import com.gps.phonetracker.numberlocator.family.tracklocation.R


@Composable
fun ScreenHome() {
    val list = listOf(
        Function(
            1,
            "Bộ định vị số",
            "Gọi số điện thoại tất cả vị trí liên hệ được lưu trữ",
            R.drawable.baseline_map_24,
            android.graphics.Color.RED
        ),
        Function(
            2,
            "Thông tin thẻ sim",
            "Dễ dàng xem và kiểm tra thẻ sim của bạn",
            R.drawable.baseline_sim_card_24,
            android.graphics.Color.GREEN
        ),
        Function(
            3,
            "Gần theo địa điểm",
            "Bạn có thể tìm hiểu địa điểm lân cận trên Google",
            R.drawable.baseline_location_on_24,
            android.graphics.Color.BLUE
        ),
        Function(
            4,
            "Bộ định vị số GPS",
            "Gọi số điện thoại tất cả vị trí liên hệ được lưu trữ",
            R.drawable.baseline_phone_iphone_24,
            android.graphics.Color.CYAN
        )
    )
    Column() {
        Toolbar()
        FunctionSection(list)
    }

}

@Composable
fun Toolbar() {
    Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
        Text(text = "Vị trí số điện\nthoại", fontWeight = FontWeight.Bold, fontSize = 25.sp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp), Arrangement.SpaceEvenly
        ) {
            Icon(Icons.Default.Notifications, contentDescription = "")
            Icon(Icons.Default.Person, contentDescription = "")
            Icon(Icons.Default.Settings, contentDescription = "")
        }
    }
}

@Composable
fun FunctionSection(list: List<Function>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 3.dp),
    ) {
        items(list.size) {
            Item(list[it])
        }
    }
}

@Composable
fun Item(item: Function) {
    val mContext = LocalContext.current
    Box(modifier = Modifier.padding(10.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color(item.tint))
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 25.dp,
                    bottom = 25.dp
                )
                .clickable {
                    when (item.id) {
                        1 -> mContext.startActivity(Intent(mContext, NumberLocator::class.java))
                        1 -> mContext.startActivity(Intent(mContext, NumberLocator::class.java))
                        1 -> mContext.startActivity(Intent(mContext, NumberLocator::class.java))
                        4 -> mContext.startActivity(Intent(mContext, LiveLocation::class.java))
                    }
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color.White)
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = item.icon),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color(item.tint))
                )
            }
            Text(
                text = item.title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 25.dp),
                fontSize = 18.sp
            )
            Text(text = item.mess, color = Color.White, textAlign = TextAlign.Center)
        }
    }
}