package com.example.app_name.koinDI

import com.example.app_name.view.fragments.users.UsersFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val usersModule = module {
    viewModel {
        UsersFragmentViewModel(
            get()
        )
    }
}