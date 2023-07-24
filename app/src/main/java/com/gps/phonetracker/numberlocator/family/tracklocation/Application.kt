package com.gps.phonetracker.numberlocator.family.tracklocation

import android.os.Bundle
import com.access.pro.activity.BaseActivity
import com.access.pro.config.AdsConfigModel

class Application :BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AdsConfigModel.GG_APP_OPEN = BuildConfig.GG_APP_OPEN
        AdsConfigModel.GG_BANNER = BuildConfig.GG_BANNER
        AdsConfigModel.GG_NATIVE = BuildConfig.GG_NATIVE
        AdsConfigModel.GG_FULL = BuildConfig.GG_FULL
        AdsConfigModel.GG_REWARDED = BuildConfig.GG_REWARDED

        super.onCreate(savedInstanceState)

    }
}