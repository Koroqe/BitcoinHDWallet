package com.koroqe.bitcoinhdwallet.di.module

import android.content.Context
import com.koroqe.bitcoinhdwallet.data.prefs.SharedPrefs
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton


/**
 * Created by danielyakovlev on 6/16/17.
 */

@Module
class DataModule {

    // region Realm
    @Provides
    @Singleton
    fun provideRealm(context: Context): Realm {
        Realm.init(context)
        val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)
        return Realm.getDefaultInstance()
    }

    // endregion Realm

//    // region Retrofit
//    @Singleton
//    @Provides
//    @Named("cached")
//    fun provideOkHttpClient(): OkHttpClient {
//        // Logging interceptor
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        // Signing interceptor
//        val signingInterceptor = Interceptor { chain ->
//            val newRequest = buildSignedRequest(chain, ApiHelper.keyPair?.secretSeed ?: "")
//            chain.proceed(newRequest)
//        }
//
//        val cache = Cache(Environment.getDownloadCacheDirectory(), 10 * 1024 * 1024)
//        val clientBuilder = OkHttpClient.Builder()
//                .readTimeout(1, TimeUnit.MINUTES)
//                .writeTimeout(1, TimeUnit.MINUTES)
//                .cache(cache)
//        clientBuilder.interceptors().add(loggingInterceptor)
//        clientBuilder.interceptors().add(signingInterceptor)
//
//        return clientBuilder.build()
//    }


//    @Singleton
//    @Provides
//    @Named("non_cached")
//    fun provideNonCachedOkHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder()
//                .readTimeout(1, TimeUnit.MINUTES)
//                .writeTimeout(1, TimeUnit.MINUTES)
//                .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideGson(): Gson {
//        return GsonBuilder()
//                .setLenient()
//                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//                .create()
//    }
//
//    @Singleton
//    @Provides
//    fun provideRetrofit(gson: Gson, @Named("cached") client: OkHttpClient): Retrofit.Builder =
//            Retrofit.Builder()
//                    .client(client)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//
//    @Singleton
//    @Provides
//    fun provideAppApiHelper(stellarServer: Server, horizonService: HorizonService)
//            = AppApiHelper(stellarServer, horizonService)
//
//    @Singleton
//    @Provides
//    fun provideAppDbHelper() = AppDbHelper()

    @Singleton
    @Provides
    fun provideSharedPrefs() = SharedPrefs()

    companion object {
        val SUBMIT_TRANSACTION_TIMEOUT = 20 * 1000
        val SIGNATURE_VALID_SEC = 60
    }
}