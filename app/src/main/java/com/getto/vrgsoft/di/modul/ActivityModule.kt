package com.getto.vrgsoft.di.modul

import android.support.v7.app.AppCompatActivity
import com.getto.vrgsoft.ui.main.MainContract
import com.getto.vrgsoft.ui.main.MainPresenter
import com.getto.vrgsoft.di.ActivityContext
import com.getto.vrgsoft.di.PerActivity
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityContext
    fun provideContext() = activity

    @Provides
    fun provideActivity() = activity

    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

    @Provides
    @PerActivity
    fun provideMainPresenter (mainPresenter: MainPresenter<MainContract.View>) :
            MainContract.Presenter<MainContract.View> = mainPresenter
}