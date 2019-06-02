package com.getto.vrgsoft.di.component

import com.getto.vrgsoft.di.PerActivity
import com.getto.vrgsoft.di.modul.ActivityModule
import com.getto.vrgsoft.ui.main.MainActivity
import dagger.Component


@PerActivity
@Component(dependencies = [(ApplicationComponent::class)], modules = [(ActivityModule::class)])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}