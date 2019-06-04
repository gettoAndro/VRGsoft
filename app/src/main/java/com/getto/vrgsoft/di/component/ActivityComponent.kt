package com.getto.vrgsoft.di.component

import com.getto.vrgsoft.di.PerActivity
import com.getto.vrgsoft.di.modul.ActivityModule
import com.getto.vrgsoft.ui.emailed.description.EmailedDetailsActivity
import com.getto.vrgsoft.ui.favorite.FavoriteActivity
import com.getto.vrgsoft.ui.main.MainActivity
import com.getto.vrgsoft.ui.shared.description.SharedDetailsActivity
import com.getto.vrgsoft.ui.viewed.description.ViewedDetailsActivity
import dagger.Component


@PerActivity
@Component(dependencies = [(ApplicationComponent::class)], modules = [(ActivityModule::class)])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(emailed: EmailedDetailsActivity)
    fun inject(favorite: FavoriteActivity)
    fun inject(sharedDetailsActivity: SharedDetailsActivity)
    fun inject(viewedDetailsActivity: ViewedDetailsActivity)
}