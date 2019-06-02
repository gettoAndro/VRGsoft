package com.getto.vrgsoft

import android.app.Application
import android.content.Context
import android.util.Log
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.LogStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.getto.vrgsoft.di.component.ApplicationComponent
import com.getto.vrgsoft.di.component.DaggerApplicationComponent
import com.getto.vrgsoft.di.modul.ApplicationModule

class VrgApp : Application() {

    lateinit var appComponent: ApplicationComponent

    companion object {
        var context: Context?= null
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

        appComponent.inject(this)
        context = applicationContext
        initLogger()
    }

    private fun initLogger() {
        val strategy = PrettyFormatStrategy.newBuilder()
                .methodCount(0)
                .tag("SJ")
                .logStrategy(object : LogStrategy {

                    private var last: Int = 0

                    override fun log(priority: Int, tag: String?, message: String) {
                        Log.println(priority, randomKey() + tag!!, message)
                    }

                    private fun randomKey(): String {
                        var random = (10 * Math.random()).toInt()
                        if (random == last) {
                            random = (random + 1) % 10
                        }
                        last = random
                        return random.toString()
                    }
                })
                .build()


        Logger.addLogAdapter(object : AndroidLogAdapter(strategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

}