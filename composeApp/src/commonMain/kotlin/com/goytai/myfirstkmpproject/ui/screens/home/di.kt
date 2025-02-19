package com.goytai.myfirstkmpproject.ui.screens.home

import com.goytai.myfirstkmpproject.infra.di.ScreenModelParams
import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.instance

val homeScreenDi = DI.Module("HomeScreenDI") {
  bindFactory { params: ScreenModelParams ->
    HomeScreenModel(
      params = params,
      taskRepository = instance()
    )
  }
}