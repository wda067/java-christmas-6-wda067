# 크리스마스 프로모션

## 기능 목록
- [x] 식당 방문 날짜를 입력 받는 기능 - InputView#inputVisitDate()
  - [x] 숫자를 입력했는지 검증 - OrderService#validateVisitDate()
  - [x] 1에서 31까지의 숫자인지 검증 - OrderService#validateVisitDate()
  - [x] 예외 발생시 예외 메시지 출력 및 재 입력 기능 - ChristmasController#handleInvalidVisitDate()
- [x] 주문 메뉴와 개수를 입력 받는 기능 - InputView#inputMenuAndCount()
  - [x] 입력 메시지 출력 - InputView#printInputMenuAndCountMessage()
  - [x] 메뉴판 출력 - InputView#printMenuTable()
  - [x] 이벤트 주의사항 출력 - InputView#printEventNotice()
  - [x] 입력받은 String을 Map으로 변환 - OrderService#convertStringToCollection()
  - [x] 메뉴 형식이 예시와 다른 경우 예외 발생 - OrderService#validateMenuAndCount()
  - [x] 쉼표로 구분하여 입력하였는지 검증 - OrderService#validateMenuAndCount()
  - [x] 중복된 메뉴를 입력할 시 예외 발생 - OrderService#validateMenuAndCount()
  - [x] 메뉴의 개수를 1개 이상 입력했는지 검증 - OrderService#validateMenuAndCount()
  - [x] 메뉴판에 없는 메뉴 주문시 예외 발생 - OrderService#validateMenuAndCount()
  - [x] 음료만 주문 시 예외 발생 - OrderService#validateMenuAndCount()
  - [x] 총 주문 개수가 20개를 초과할 시 예외 발생 - OrderService#validateMenuAndCount()
  - [x] 예외 발생시 예외 메시지 출력 및 재 입력 기능 - ChristmasController#handleInvalidMenuAndCount()
- [x] 주문 메뉴를 순서 관계없이 출력하는 기능 - OutputView#printOrderMenu()
- [x] 할인 전 총 주문 금액 출력하는 기능 - OutputView#printTotalOrderAmountBeforeDiscount()
  - [x] 할인 전 총 주문 금액을 계산하는 기능 - OrderService#calculateTotal()
  - [x] 총 주문 금액이 1만 원 이상일 시 이벤트 적용 - ChristmasController#setBenefitDetails()
- [x] 증정 메뉴 출력 기능 - OutputView#printGiftMenu()
  - [x] 금액이 12만 원 이상이면 샴페인 1개 증정 - OrderService#isChampagneProvided()
- [x] 혜택 내역 출력 기능 - OutputView#printBenefitDetails()
  - [x] 크리스마스 디데이 할인 - EventService#calculateXmasDiscount()
  - [x] 방문 날짜의 평일/주말 구분하여 할인 - EventService#determineDayDiscount()
    - [x] 방문 날짜의 평일/주말 구분 기능 - EventService#isWeekend()
    - [x] 평일에는 디저트 메뉴 할인 - EventService#calculateWeekdayDiscount()
    - [x] 주말에는 메인 메뉴 할인 - EventService#calculateWeekendDiscount()
  - [x] 특별 할인 - EventService#calculateSpecialDiscount()
    - [x] 방문 날짜에 별이 있는지 여부 확인 EventService#hasStarInCalendar()
  - [x] 증정 이벤트 할인 - ChristmasController#setGiftEventBenefit()
- [x] 총 혜택 금액 출력 기능 - OutputView#printTotalBenefit()
- [x] 할인 후 예상 결제 금액 출력 기능 - OutputView#printExpectedTotalAfterDiscount()
- [x] 12월 이벤트 배지 출력 기능 - OutputView#printEventBadge()
  - [x] 총 혜택 금액에 따라 별/트리/산타 구분 - EventService#determineBadge()

## 기능 요구 사항
크리스마스 프로모션을 구현한다.
### 이벤트 목표
1. 중복된 할인과 증정을 허용해서, 고객들이 혜택을 많이 받는다는 것을 체감할 수 있게 하는 것
2. 올해 12월에 지난 5년 중 최고의 판매 금액을 달성
3. 12월 이벤트 참여 고객의 5%가 내년 1월 새해 이벤트에 재참여하는 것

### 12월 이벤트 계획
- 크리스마스 디데이 할인
  - 이벤트 기간: 2023.12.1 ~ 2023.12.25
  - 1000원으로 시작, 날마다 할인 금액이 100원씩 증가
  - 총 주문 금액에서 해당 금액만큼 할인
- 평일 할인(일~목): 평일에는 디저트 메뉴를 1개당 2,023원 할인
- 주말 할인(금,토): 주말에는 메인 메뉴를 1개당 2,023원 할인
- 특별 할인: 이벤트 달력에 별이 있으면 총 주문 금액에서 1,000원 할인
- 증정 이벤트: 할인 전 총 주문 금액이 12만 원 이상일 때, 샴페인 1개 증정
- 이벤트 기간: '크리스마스 디데이 할인'을 제외한 다른 이벤트는 12월 내내 적용

### 혜택 금액에 따른 12월 이벤트 배지 부여
- 총 혜택 금액에 따라 다른 이벤트 베지 부여, 이 배지는 2024 새해 이벤트에서 활용 예정
- 배지에 따라 새해 이벤트 참여 시, 각각 다른 새해 선물을 증정 예정
  - 5천 원 이상: 별
  - 1만 원 이상: 트리
  - 2만 원 이상: 산타

### 고객에게 안내할 이벤트 주의 사항
  - 총 주문 금액 10,000원 이상부터 이벤트가 적용
  - 음료만 주문 시, 주문 불가
  - 메뉴는 한 번에 최대 20개까지만 주문 가능
    - (e.g. 시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1의 총 개수는 7개)

### '12월 이벤트 플래너' 개발 요청 사항
  - 고객들이 식당에 방문할 날짜와 메뉴를 미리 선택하면 이벤트 플래너가 다음 항목을 보여주기를 기대
    - 주문 메뉴
    - 할인 전 총 주문 금액
    - 증정 메뉴
    - 혜택 내역
    - 총 혜택 금액
    - 할인 후 예상 결제 금액
    - 12월 이벤트 배지 내용
  - 12월 중 식당 예상 방문 날짜를 입력
    - 1 이상 31 이하의 숫자
    - 예외 시 에러 메시지 출력
  - 주문 할 메뉴와 개수를 입력
    - 예외 시 에러 메시지 출력
  - 주문 메뉴의 출력 순서는 자유
  - 총 혜택 금액에 다라 이벤트 배지의 이름을 다르게 출력
  - 총 혜택 금액 = 할인 금액의 합계 + 증정 메뉴의 가격
  - 할일 후 예상 결제 금액 = 할인 전 총 주문 금액 - 할인 금액
  - 증정 메뉴 출력
    - 해당하지 않을 경우 "없음" 출력
  - 고객에게 적용된 혜택 내역 출력
    - 적용된 이벤트가 없다면 "없음" 출력
    - 여러 개의 이벤트가 적용 시, 순서는 자유롭게 출력
  - 이벤트 배지 출력
    - 해당하지 않을 경우 "없음" 출력