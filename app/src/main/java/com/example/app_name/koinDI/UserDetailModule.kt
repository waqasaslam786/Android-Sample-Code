package com.example.app_name.koinDI

import com.example.app_name.view.fragments.userDetail.UserDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userDetailModule = module {
    viewModel {
        UserDetailViewModel(
            get()
        )
    }
}