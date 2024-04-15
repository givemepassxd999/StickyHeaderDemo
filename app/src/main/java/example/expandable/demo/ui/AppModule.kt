package example.expandable.demo.ui

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class AppModule {
    val vmModule = module {
        viewModel { MainViewModel() }
    }
}