package kr.co.kurly.feature.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kr.co.kurly.core.repository.ProductRepository
import kr.co.kurly.core.ui.BaseViewModel
import kr.co.kurly.core.ui.UiEvent
import kr.co.kurly.core.ui.UiSideEffect
import kr.co.kurly.core.ui.UiState
import javax.inject.Inject

enum class HomeUiType {
  NONE,
  LOADED
}

data class UiModel(
  val name: String
) {
//  companion object {
//    fun mapperToUi(dtos: List<ArticleDto>): ImmutableList<UiModel> {
//      return dtos
//        .map { UiModel(it.name) }
//        .toImmutableList()
//    }
//  }
}

data class HomeUiState(
  val uiType: HomeUiType = HomeUiType.NONE,
  val uiModels: ImmutableList<UiModel> = persistentListOf(),
  val isLoading: Boolean = false
) : UiState

sealed interface HomeUiEvent : UiEvent {

}

sealed interface HomeUiSideEffect : UiSideEffect {
  data object Load : HomeUiSideEffect
}

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val productRepository: ProductRepository,
) : BaseViewModel<HomeUiState, HomeUiEvent, HomeUiSideEffect>() {

  override fun createInitialState(): HomeUiState {
    return HomeUiState()
  }

  override fun handleEvent(event: HomeUiEvent) {
    when (event) {
      else -> {}
    }
  }

  init {
    setEffect { HomeUiSideEffect.Load }
  }

  fun fetchData() {
    viewModelScope.launch {
      productRepository.getSectionProduct(2)
        .onStart { }
        .onCompletion { }
        .catch { setErrorState(it) }
        .collect {
          println("apiResultLog : ${it.data}")
//          setState {
//            copy(
//              uiType = FirstUiType.LOADED,
//              uiModels = UiModel.mapperToUi(it)
//            )
//          }
        }
    }
  }
}