package com.goytai.myfirstkmpproject.ui.screens.home

import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.instance


val homeScreenDi = DI.Module("HomeScreenDI") {
  bindFactory { params: HomeScreenModelParams ->
    HomeScreenModel(
      params = params,
      taskRepository = instance()
    )
  }
}