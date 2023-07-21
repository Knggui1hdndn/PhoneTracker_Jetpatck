package com.gps.phonetracker.numberlocator.family.tracklocation

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.gps.phonetracker.numberlocator.family.tracklocation.screens.ScreenHome


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScreenHome()

        }
    }
}


