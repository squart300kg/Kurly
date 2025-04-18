package kr.co.kurly.core.network

import android.content.Context

// 원래 해당 코드는 'network'프로젝트가 아닌, 'file' 또는 'local'과 같은 프로젝트에 존재해야 합니다.
// 하지만 RestAPI연동했을 때를 가정하여 코드를 작성했기에 'network'프로젝트에 위치시킵니다.
interface FileProvider {
  fun getJsonFromAsset(fileName: String): String
}

class AssetFileProvider(private val context: Context) : FileProvider {
  override fun getJsonFromAsset(fileName: String): String =
    context.assets.open(fileName).bufferedReader().use { it.readText() }
}