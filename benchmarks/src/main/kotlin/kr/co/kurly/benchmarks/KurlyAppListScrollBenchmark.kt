package kr.co.kurly.benchmarks

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import kr.co.kurly.test.testing.ui.TestTag.GRID_ITEMS
import kr.co.kurly.test.testing.ui.TestTag.HORIZONTAL_ITEMS
import kr.co.kurly.test.testing.ui.TestTag.PRODUCT_LIST
import kr.co.kurly.test.testing.ui.TestTag.VERTICAL_ITEMS
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern

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
    iterations = 5,
    startupMode = StartupMode.WARM,
    setupBlock = {
      pressHome()
      startActivityAndWait()
    }
  ) {
    // PRODUCT_LIST 찾기
    val productList = device.waitAndFindObject(By.res(PRODUCT_LIST))

    // 수직 스크롤 (Grid, Vertical 대응)
    repeat(4) { device.fling(element = productList, direction = Direction.DOWN) }
    repeat(2) { device.fling(element = productList, direction = Direction.UP) }

    // 수평 스크롤용 첫 horizontal 섹션 찾기
    device.findObject(By.res(Pattern.compile(".*_${HORIZONTAL_ITEMS}")))?.let { horizontalSection ->
      repeat(2) { device.fling(element = horizontalSection, direction = Direction.RIGHT) }
      repeat(1) { device.fling(element = horizontalSection, direction = Direction.LEFT) }
    }
  }
}