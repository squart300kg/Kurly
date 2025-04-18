package kr.co.kurly.core.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before

@OptIn(ExperimentalCoroutinesApi::class)
class ProductRepositoryTest {

  private val testScope = TestScope(UnconfinedTestDispatcher())

  private lateinit var repository: ProductRepositoryImpl

  private lateinit var remoteApi: RemoteApi

  @Before
  fun setup() {

  }

}