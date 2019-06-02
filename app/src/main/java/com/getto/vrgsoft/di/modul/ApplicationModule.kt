package com.getto.vrgsoft.di.modul

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.getto.vrgsoft.util.dataLogger
import com.getto.vrgsoft.util.domainLogger
import com.getto.vrgsoft.util.netLogger
import com.catchman.data.DataLogger
import com.getto.vrgsoft.domain.DomainLogger
import com.getto.vrgsoft.domain.executor.PostExecutionThread
import com.getto.vrgsoft.UiThread
import com.example.data.ApiConst
import com.getto.vrgsoft.data.AppDatabase
import com.getto.vrgsoft.data.nyt.storage.CountryStorageImpl
import com.getto.vrgsoft.di.ApplicationContext
import com.getto.vrgsoft.data.nyt.service.NytServiceImpl
import com.getto.vrgsoft.data.nyt.service.NyTypesService
import com.getto.vrgsoft.data.JobExecutor
import com.getto.vrgsoft.data.interceptor.LoggingInterceptor
import com.getto.vrgsoft.data.nyt.NytGatewayImpl
import com.getto.vrgsoft.data.nyt.service.MostPopularApi
import com.getto.vrgsoft.data.nyt.storage.CountryStorage
import com.getto.vrgsoft.domain.executor.ThreadExecutor
import com.getto.vrgsoft.domain.gateway.NytGateway
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class ApplicationModule(private val app: Application) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context = app

    @Provides
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread = uiThread

    @Provides
    fun provideDataLogger(): DataLogger = dataLogger()

    @Provides
    fun provideDomainLogger(): DomainLogger = domainLogger()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        val logging = LoggingInterceptor(object : LoggingInterceptor.Logger {
            override fun log(message: String) = netLogger(message)
        }).setLevel(LoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    fun provide(client: OkHttpClient): MostPopularApi {
        return Retrofit.Builder()
                .baseUrl(ApiConst.ENDPOINT)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(MostPopularApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSferaService(nytService: NytServiceImpl): NyTypesService = nytService

    @Provides
    @Singleton
    fun provideSferaStorage(countryStorage: CountryStorageImpl): CountryStorage = countryStorage

    @Provides
    @Singleton
    fun provideSferaGateway(nytGateway: NytGatewayImpl): NytGateway = nytGateway

    @Provides
    @Singleton
    fun provideRoomDatabase() =
            Room.databaseBuilder(app.applicationContext, AppDatabase::class.java, "vrg-database")
                    .fallbackToDestructiveMigration()
                    .build()

}