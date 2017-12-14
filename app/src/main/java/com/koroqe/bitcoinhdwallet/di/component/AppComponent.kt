package com.koroqe.bitcoinhdwallet.di.component


import com.koroqe.bitcoinhdwallet.App
import com.koroqe.bitcoinhdwallet.data.Repository
import com.koroqe.bitcoinhdwallet.data.db.AppDbHelper
import com.koroqe.bitcoinhdwallet.data.prefs.SharedPrefs
import com.koroqe.bitcoinhdwallet.di.module.ApplicationModule
import com.koroqe.bitcoinhdwallet.di.module.DataModule
import com.koroqe.bitcoinhdwallet.presentation.main.MainPresenter
import dagger.Component
import io.realm.Realm
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, DataModule::class))
interface AppComponent {
    fun inject(application: App)
    fun inject(sharedPrefs: SharedPrefs)
    fun inject(appDbHelper: AppDbHelper)
//    fun inject(myAccountPresenter: MyAccountPresenter)
//    fun inject(receivePresenter: ReceivePresenter)
//    fun inject(sendPresenter: SendPresenter)
    fun inject(settingsPresenter: MainPresenter)
//    fun inject(loginPresenter: LoginPresenter)
//    fun inject(signUpPresenter: SignUpPresenter)

    fun getRepository(): Repository
    fun getSharedPrefs(): SharedPrefs
    fun getAppDbHelper(): AppDbHelper
    fun getRealm(): Realm
}
