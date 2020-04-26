package com.mreigar.postapp.injection

import android.app.Activity
import android.content.Context
import com.mreigar.presentation.presenter.PostDetailsPresenter
import com.mreigar.presentation.presenter.PostDetailsViewTranslator
import com.mreigar.presentation.presenter.PostListPresenter
import com.mreigar.presentation.presenter.PostListViewTranslator
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

inline fun <reified T : Any> Activity.injectActivity(): Lazy<T> = inject { parametersOf(this) }

object AppModules {

    val presentationModules = module {
        factory { (view: Context) -> PostListPresenter(view as PostListViewTranslator, get(), get()) }
        factory { (view: Context) -> PostDetailsPresenter(view as PostDetailsViewTranslator, get(), get(), get()) }
    }
}