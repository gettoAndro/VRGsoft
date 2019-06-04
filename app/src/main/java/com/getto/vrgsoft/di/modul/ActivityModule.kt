package com.getto.vrgsoft.di.modul

import android.support.v7.app.AppCompatActivity
import com.getto.vrgsoft.ui.main.MainContract
import com.getto.vrgsoft.ui.main.MainPresenter
import com.getto.vrgsoft.di.ActivityContext
import com.getto.vrgsoft.di.PerActivity
import com.getto.vrgsoft.ui.emailed.description.EmailedDetailsContract
import com.getto.vrgsoft.ui.emailed.description.EmailedDetailsPresenter
import com.getto.vrgsoft.ui.favorite.FavoriteContract
import com.getto.vrgsoft.ui.favorite.FavoritePresenter
import com.getto.vrgsoft.ui.shared.description.SharedDetailsContract
import com.getto.vrgsoft.ui.shared.description.SharedDetailsPresenter
import com.getto.vrgsoft.ui.viewed.description.ViewedDetailsContract
import com.getto.vrgsoft.ui.viewed.description.ViewedDetailsPresenter
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

    @Provides
    @PerActivity
    fun provideEmailedDetailsPresenter (emailedDetailsPresenter: EmailedDetailsPresenter<EmailedDetailsContract.View>) :
            EmailedDetailsContract.Presenter<EmailedDetailsContract.View> = emailedDetailsPresenter

    @Provides
    @PerActivity
    fun provideFavoritePresenter (favoritePresenter: FavoritePresenter<FavoriteContract.View>) :
            FavoriteContract.Presenter<FavoriteContract.View> = favoritePresenter


    @Provides
    @PerActivity
    fun provideSharedDetailsPresenter (sharedDetailsPresenter: SharedDetailsPresenter<SharedDetailsContract.View>) :
            SharedDetailsContract.Presenter<SharedDetailsContract.View> = sharedDetailsPresenter

    @Provides
    @PerActivity
    fun provideViewedDetailsPresenter (viewedDetailsPresenter: ViewedDetailsPresenter<ViewedDetailsContract.View>) :
            ViewedDetailsContract.Presenter<ViewedDetailsContract.View> = viewedDetailsPresenter

}