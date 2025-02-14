package com.goytai.myfirstkmpproject.domain.repository

import com.goytai.myfirstkmpproject.AppTheme
import kotlinx.coroutines.flow.Flow

interface IAppSettingsRepository {
  suspend fun updateAppTheme(mode: AppTheme)

  fun getAppTheme(): Flow<AppTheme>
}