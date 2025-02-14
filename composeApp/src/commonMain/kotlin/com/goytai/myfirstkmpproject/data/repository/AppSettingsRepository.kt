package com.goytai.myfirstkmpproject.data.repository

import androidx.datastore.core.DataStore
import com.goytai.myfirstkmpproject.AppSettings
import com.goytai.myfirstkmpproject.AppTheme
import com.goytai.myfirstkmpproject.domain.repository.IAppSettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppSettingsRepository(val dataStore: DataStore<AppSettings>) : IAppSettingsRepository {
  override suspend fun updateAppTheme(mode: AppTheme) {
    dataStore.updateData {
      it.copy(appTheme = mode)
    }
  }

  override fun getAppTheme(): Flow<AppTheme> {
    return dataStore.data.map { it.appTheme }
  }
}