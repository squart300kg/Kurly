package kr.co.kurly.core.network

import android.content.Context

interface FileProvider {
  fun getJsonFromAsset(fileName: String): String
}

class AssetFileProvider(private val context: Context) : FileProvider {
  override fun getJsonFromAsset(fileName: String): String =
    context.assets.open(fileName).bufferedReader().use { it.readText() }
}