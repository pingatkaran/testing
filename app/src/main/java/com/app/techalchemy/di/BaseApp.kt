package com.app.techalchemy.di

import android.app.Application
import com.app.techalchemy.R
import dagger.hilt.android.HiltAndroidApp
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump

@HiltAndroidApp
class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        ViewPump.builder()
            .addInterceptor(
                CalligraphyInterceptor(
                    CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/Centra No2.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
                )
            ).build().let { viewPump ->
                ViewPump.init(viewPump)
            }
    }

}