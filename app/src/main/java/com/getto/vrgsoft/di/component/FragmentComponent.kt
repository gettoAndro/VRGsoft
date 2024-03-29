package com.getto.vrgsoft.di.component

import com.getto.vrgsoft.di.PerFragment
import com.getto.vrgsoft.di.modul.ActivityModule
import com.getto.vrgsoft.di.modul.FragmentModule
import com.getto.vrgsoft.ui.emailed.EmailedFragment
import com.getto.vrgsoft.ui.shared.SharedFragment
import com.getto.vrgsoft.ui.viewed.ViewedFragment
import dagger.Component

@PerFragment
@Component(dependencies = [(ApplicationComponent::class)], modules = [(FragmentModule::class),(ActivityModule::class)])
interface FragmentComponent {

    fun inject(emailedFragment: EmailedFragment)
    fun inject(shared: SharedFragment)
    fun inject(viewed: ViewedFragment)

}