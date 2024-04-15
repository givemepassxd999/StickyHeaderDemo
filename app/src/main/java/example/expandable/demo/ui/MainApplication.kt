package example.expandable.demo.ui

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // start Koin!
        startKoin {
            // Android context
            androidContext(this@MainApplication)
            // koin modules
            val module = AppModule()
            val list = listOf(module.vmModule)
            modules(list)
        }
    }
}