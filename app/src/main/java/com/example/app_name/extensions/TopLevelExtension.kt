package com.example.app_name.extensions

import androidx.work.Constraints
import androidx.work.NetworkType
import com.example.app_name.util.Event

fun <T> T.wrapWithEvent() = Event(this)

val internetWorkerConstraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
    .build()