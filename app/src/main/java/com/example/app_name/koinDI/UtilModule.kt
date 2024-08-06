package com.example.app_name.koinDI

import com.example.app_name.api.SharedWebService
import com.example.app_name.backend.MyCustomApp
import com.example.app_name.util.MaterialDialogHelper
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val utilModule = module {

    single { MaterialDialogHelper() }

    single { SharedWebService(get(), androidApplication() as MyCustomApp) }


}