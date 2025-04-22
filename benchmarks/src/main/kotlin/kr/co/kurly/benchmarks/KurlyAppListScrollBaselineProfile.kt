package kr.co.kurly.benchmarks

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.uiautomator.By
import org.junit.Rule
import org.junit.Test

@RequiresApi(Build.VERSION_CODES.P)
class KurlyAppListScrollBaselineProfile {

  @get:Rule
  val baselineProfileRule = BaselineProfileRule()

  @Test
  fun generate() =
    baselineProfileRule.collect("kr.co.kurly.ssy") {
      startActivityAndWait()

      // 전체 리스트 영역
      val productList = device.waitAndFindObject(By.res("productList"))
      // 수평 스크롤 대상 요소 가져오기
      val horizontalItems = productList.waitAndFindObject(By.res("horizontalItems"))
      // 수평 스크롤 실행 (RIGHT 방향)
      device.flingElementRight(horizontalItems)
      // 수직 스크롤 (DOWN)
      device.flingElementDown(productList)
    }
}