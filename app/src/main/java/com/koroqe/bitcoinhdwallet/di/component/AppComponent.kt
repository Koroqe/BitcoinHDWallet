package com.koroqe.bitcoinhdwallet.di.component


import com.koroqe.bitcoinhdwallet.App
import com.koroqe.bitcoinhdwallet.data.Repository
import com.koroqe.bitcoinhdwallet.data.db.AppDbHelper
import com.koroqe.bitcoinhdwallet.data.prefs.SharedPrefs
import com.koroqe.bitcoinhdwallet.di.module.ApplicationModule
import com.koroqe.bitcoinhdwallet.di.module.DataModule
import com.koroqe.bitcoinhdwallet.presentation.login.LoginActivity
import com.koroqe.bitcoinhdwallet.presentation.login.fragments.login.LoginPresenter
import com.koroqe.bitcoinhdwallet.presentation.login.fragments.restore.RestorePresenter
import com.koroqe.bitcoinhdwallet.presentation.main.fragments.main.MainPresenter
import com.koroqe.bitcoinhdwallet.presentation.main.fragments.receive.ReceivePresenter
import com.koroqe.bitcoinhdwallet.presentation.main.fragments.send.SendPresenter
import com.koroqe.bitcoinhdwallet.presentation.qrcode.QrPresenter
import com.koroqe.bitcoinhdwallet.presentation.settings.SettingsPresenter
import com.koroqe.bitcoinhdwallet.presentation.settings.fragments.showseed.ShowSeedPresenter
import dagger.Component
import io.realm.Realm
import javax.inject.Singleton

/**
 * Created by Koroqe on 14-Dec-17.
 *
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, DataModule::class))
interface AppComponent {
    fun inject(application: App)
    fun inject(sharedPrefs: SharedPrefs)
    fun inject(appDbHelper: AppDbHelper)

    fun inject(receivePresenter: ReceivePresenter)
    fun inject(sendPresenter: SendPresenter)
    fun inject(mainPresenter: MainPresenter)
    fun inject(loginPresenter: LoginPresenter)
    fun inject(restorePresenter: RestorePresenter)
    fun inject(showSeedPresenter: ShowSeedPresenter)
    fun inject(settingsPresenter: SettingsPresenter)
    fun inject(qrPresenter: QrPresenter)

    fun inject(activity: LoginActivity)

    fun getRepository(): Repository
    fun getSharedPrefs(): SharedPrefs
    fun getAppDbHelper(): AppDbHelper
    fun getRealm(): Realm
}
