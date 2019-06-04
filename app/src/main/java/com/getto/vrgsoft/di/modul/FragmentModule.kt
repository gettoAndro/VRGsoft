package com.getto.vrgsoft.di.modul

import com.getto.vrgsoft.di.PerFragment
import com.getto.vrgsoft.ui.emailed.EmailedContract
import com.getto.vrgsoft.ui.emailed.EmailedPresenter
import com.getto.vrgsoft.ui.shared.SharedContract
import com.getto.vrgsoft.ui.shared.SharedPresenter
import com.getto.vrgsoft.ui.viewed.ViewedContract
import com.getto.vrgsoft.ui.viewed.ViewedPresenter
import dagger.Module
import dagger.Provides


/**
 * Created by ujujzk on 17.07.2018
 * Softensy Digital Studio
 * softensiteam@gmail.com
 */

@Module
class FragmentModule {

    @Provides
    @PerFragment
    fun provideEmailedPresenter (presenter: EmailedPresenter<EmailedContract.View>) : EmailedContract.Presenter<EmailedContract.View> = presenter

    @Provides
    @PerFragment
    fun provideSharedPresenter(presenter : SharedPresenter<SharedContract.View>) : SharedContract.Presenter<SharedContract.View> = presenter

    @Provides
    @PerFragment
    fun provideViewedPresenter(presenter : ViewedPresenter<ViewedContract.View>) : ViewedContract.Presenter<ViewedContract.View> = presenter

//    @Provides
//    @PerFragment
//    fun provideLeisurePresenter (presenter: LeisurePresenter<LeisureContract.View>) : LeisureContract.Presenter<LeisureContract.View> = presenter
//
//    @Provides
//    @PerFragment
//    fun provideAtmoPresenter (presenter: AtmoPresenter<AtmoContract.View>) : AtmoContract.Presenter<AtmoContract.View> = presenter
}