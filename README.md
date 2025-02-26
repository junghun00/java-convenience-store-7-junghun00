# java-convenience-store-precourse

## 🛠️ 요구 사항 정리

### 💬 1. 입/출력 정리

#### 1-1. 상품명, 수량 입력

- [x] 보유 상품 안내 메세지 출력
- [x] [상품명-수량] 형식으로 입력 받는다
  - 형식이 맞지 않으면 에러
  - 상품이 없으면, 에러
  - 수량 부족 시, 에러

#### 1-2. 멤버십 할인 여부

- [x] 할인 여부 입력 받기 (Y/N)
- [x] 영수증 출력

#### 1-3 추가 구매 여부

- [x] 추가 구매 여부 입력 받기 (Y/N)
  - (Y) 프로그램 재시작
  - (N) 프로그램 종료

### 📦 2. 재고 관리

- [x] 재고가 충분한가 확인
- [x] 재고가 부족할 시 에러 메세지 출력 후 다시 입력

### 🎁 3. 프로모션 할인

- [x] 프로모션 진행 상품인지 확인 한다.
  - 1+1 또는 2+1 인지 확인
- [x] 프로모션 적용 상품에 대해 가져오지 않은 경우
    - (Y) 1개 추가 증정
    - (N) 받지 않는다.
``` 
현재 {상품명}은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)
```
- [x] 프로모션 재고가 충분한지 확인한다.
  - 부족할 시 부족한 재고만큼 안내한다
  - 정가 결제 여부 확인
    - (Y) 부족한 재고 만큼 정가 결제
    - (N) 프로모션 미적용 상품 제외 후 결제
```
현재 {상품명} {수량}개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)
```

### 💳 4. 멤버십 할인

- [x] 멤비십 할인 여부 묻기
```
멤버십 할인을 받으시겠습니까? (Y/N)
```
- [x] 할인 금액 구하기


### 🧾 5. 영수증 출력

- [x] 영수증 출력
```
==============W 편의점================
상품명		수량	금액
콜라		3 	3,000
에너지바 		5 	10,000
=============증	정===============
콜라		1
====================================
총구매액		8	13,000
행사할인			-1,000
멤버십할인			-3,000
내실돈			 9,000
```

### 🙇🏼 6. 재구매 의사

- [x] 재구매 의사 묻기
  - (Y) Store 재고 업데이트 하기
  - (N) 프로그램 종료
```
감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
```
## ❌ 예외 사항 정리

### 1. 구매할 상품명, 수량 입력

- [상품명-수량] 형식으로 입력 받는다.
- 여러 개 구매 시 쉼표(,) 로 구분
- 재고에 없는 상품일 시 에러
- 남은 수량 보다 입력 수량이 많을 시 에러
- 수량은 숫자로만 입력 받는다

### 2. 여부 확인

- (Y/N) 으로만 입력 받기
  - Y 또는 N 외 문자 입력 시 에러