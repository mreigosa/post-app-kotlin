package com.mreigar.presentation.injection

import com.mreigar.presentation.viewmodel.PostListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModules {

    val presentationModules = module {
        viewModel { PostListViewModel(get(), get()) }
    }
}
