package com.getto.vrgsoft.di.component

import android.app.Application
import android.content.Context
import com.getto.vrgsoft.domain.executor.PostExecutionThread
import com.getto.vrgsoft.di.ApplicationContext
import com.getto.vrgsoft.VrgApp
import com.getto.vrgsoft.di.modul.ApplicationModule
import com.getto.vrgsoft.domain.executor.ThreadExecutor
import com.getto.vrgsoft.domain.gateway.NytGateway
import dagger.Component
import javax.inject.Singleton



@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {

    fun inject(app: VrgApp)

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun threadExecutor(): ThreadExecutor
    fun postExecutionThread(): PostExecutionThread

    fun nytGateway(): NytGateway

}