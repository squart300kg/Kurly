package kr.co.kurly.core.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kr.co.kurly.core.network.RemoteApi
import kr.co.kurly.core.repository.RepositoryImpl
import org.junit.Before

@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryTest {

  private val testScope = TestScope(UnconfinedTestDispatcher())

  private lateinit var repository: RepositoryImpl

  private lateinit var remoteApi: RemoteApi

  @Before
  fun setup() {

  }

}