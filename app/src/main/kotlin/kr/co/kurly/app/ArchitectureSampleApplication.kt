package kr.co.kurly.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ArchitectureSampleApplication : Application() {

  override fun onCreate() {
    super.onCreate()
  }
}