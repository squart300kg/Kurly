package kr.co.kurly.benchmarks

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.uiautomator.By
import kr.co.kurly.test.testing.ui.TestTag.HORIZONTAL_ITEMS
import kr.co.kurly.test.testing.ui.TestTag.PRODUCT_LIST
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class KurlyAppListScrollBenchmark {
  @get:Rule
  val benchmarkRule = MacrobenchmarkRule()

  @Test
  fun startAndScrollCommunityListWithCompilationNone() = startAndScrollProductList(CompilationMode.None())

  @Test
  fun startAndScrollCommunityListWithBaselineProfile() = startAndScrollProductList(CompilationMode.Partial())

  @Test
  fun startAndScrollCommunityListWithFull() = startAndScrollProductList(CompilationMode.Full())

  private fun startAndScrollProductList(compilationMode: CompilationMode) = benchmarkRule.measureRepeated(
    packageName = "kr.co.kurly.ssy",
    metrics = listOf(FrameTimingMetric()),
    compilationMode = compilationMode,
    iterations = 1,
    startupMode = StartupMode.WARM,
    setupBlock = {
      pressHome()
      startActivityAndWait()
    }
  ) {
    // 전체 리스트 영역
    val productList = device.waitAndFindObject(By.res(PRODUCT_LIST))
    // 수평 스크롤 대상 요소 가져오기
    val horizontalItems = productList.waitAndFindObject(By.res(HORIZONTAL_ITEMS))
    // 수평 스크롤 실행 (RIGHT 방향)
    device.flingElementRight(horizontalItems)
    // 수직 스크롤 (DOWN)
    device.flingElementDown(productList)
  }
}