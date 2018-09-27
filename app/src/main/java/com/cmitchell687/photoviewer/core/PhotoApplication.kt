package com.cmitchell687.photoviewer.core

import android.app.Application
import com.cmitchell687.photoviewer.network.WebService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class PhotoApplication : Application() {

    private val webServiceModule : Module = applicationContext {
        bean {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://picsum.photos/")
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            retrofit.create(WebService::class.java)
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(webServiceModule))
    }
}