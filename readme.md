# 25.04.18 ~ 25.04.25 송상윤 과제 보고서

## 목차
1. 개요
2. 빌드 환경
3. 아키텍처 구조도
4. 테스트 케이스
5. BaselineProfile 성능 측정 결과


## 1. 개요
해당 readme.md는 과제를 검토하는 면접자분들의 검토 용이성을 위해 작성한 문서입니다. 목차는 크게 3가지로 나뉘며, 해당 과제가 수행된 '빌드 환경', '아키텍처 구조도'
, '테스트 케이스'와 마지막으론 스크롤 성능 개선을 위해, BaselineProfile을 시도한 결과 지표입니다.

## 2. 빌드 환경
해다 과제가 실행된 빌드 환경을 의미합니다. 만약 과제 빌드에 문제가 있을 시, 아래 빌드 환경을 참고하시면 좋습니다.
![빌드 환경.png](readme-image/%EB%B9%8C%EB%93%9C%20%ED%99%98%EA%B2%BD.png)

## 3. 아키텍처 구조도
해당 과제가 실행된 아키텍처 구조입니다. **Android공식 아키텍처**를 사용했으며, UI Layer, Domain Layer, Data Layer에 따라 앱을 구조화 하였습니다.
또한 각 Layer에는 해당 Model들이 존재합니다. (eg., *.DomainResponse, *.DtoResponse...)
![아키텍처 구조도.png](readme-image/%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98%20%EA%B5%AC%EC%A1%B0%EB%8F%84.png)

## 4. 테스트 케이스
해당 과제가 수행된 테스트 케이스로써, UI 테스트와 비즈니스 로직 단위 테스트를 진행하였습니다.

| 구분     | 테스트 영역            | 테스트 수행 클래스            | 테스트 메서드                                      | 통과 여부 |
|--------|-------------------|-----------------------|----------------------------------------------|-------|
| UI 테스트 | HomeScreen        | HomeScreenTest        | whenFirstUiLoading_thenShowNoneTypeUi        | o     |
|        |                   |                       | whenFirstUiLoaded_thenShowLoadedTypeUi       | o     |
|        |                   |                       | whenClickedProductItem_thenChangeMarkedState | o     |
|        |                   |                       | whenScrolledToEnd_thenLoadNextPage           | o     |
| 단위 테스트 | HomeViewModel     | HomeViewModelTest     | whenAppFirstLoading_thenSetNoneType          | o     |
|        |                   |                       | whenAppFirstLoaded_thenSetLoadedType         | o     |
|        |                   |                       | whenLoadedTypeIsSet_thenItemsIsAtLeastOne    | o     |
|        |                   |                       | whenTriggerProductMark_thenChangeState       | o     |
|        | ProductRepository | ProductRepositoryTest | whenTriggerProductMark_thenChangeState       | o     |

## 5. BaselineProfile 성능 측정 결과
해당 과제의 홈 화면의 성능 개선을 위해 BaselineProfile을 사용해보았습니다.

| **baselineProfile 측정 전**                                               |
|--------------------------------------------------------------------|
| ![baselineProfile전.png](readme-image/baselineProfile%EC%A0%84.png) |
| **baselineProfile 측정 후**                                               |
|                                                                    |
| ![baselineProfile후.png](readme-image/baselineProfile%ED%9B%84.png) |