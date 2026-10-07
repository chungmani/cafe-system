# API 명세서

## 1. 메뉴 목록 조회

### 기본 정보

- **Method**: `GET`
- **URL**: `/api/menus`
- **설명**: 판매 중인 메뉴 목록을 조회합니다.

### Response

**200 OK**

```json
{
  "menus": [
    {
      "menuId": 1,
      "name": "아메리카노",
      "price": 3000
    },
    {
      "menuId": 2,
      "name": "카페라떼",
      "price": 4000
    }
  ]
}
```

---

## 2. 포인트 충전

### 기본 정보

- **Method**: `POST`
- **URL**: `/api/charges/{userId}`
- **설명**: 사용자의 포인트를 충전합니다.
- **충전 단위**: 1원 = 1P

### Path Variable

| 이름 | 타입 | 설명 |
|---|---|---|
| `userId` | `Long` | 포인트를 충전할 사용자 ID |

### Request Body

```json
{
  "amount": 10000
}
```

| 필드 | 타입 | 설명 |
|---|---|---|
| `amount` | `Long` | 충전할 금액 |

### Response

**200 OK**

```json
{
  "userId": 1,
  "chargedPoint": 10000,
  "currentPoint": 15000
}
```

| 필드             | 타입              | 설명          |
|----------------|-----------------|-------------|
| `userId`       | `Long`          | 사용자 ID      |
| `chargedPoint` | `Long`          | 충전된 포인트     |
| `currentPoint` | `Long`          | 충전 후 현재 포인트 |
| `chargedAt`    | `LocalDateTime` | 충전 시간       |

### 주요 예외

- 존재하지 않는 사용자
- 충전 금액이 0 이하인 경우

---

## 3. 메뉴 주문 및 결제

### 기본 정보

- **Method**: `POST`
- **URL**: `/api/orders`
- **설명**: 사용자가 하나 이상의 메뉴를 주문하고 포인트로 결제합니다.

### Request Body

```json
{
  "userId": 1,
  "items": [
    {
      "menuId": 1,
      "quantity": 2
    },
    {
      "menuId": 2,
      "quantity": 1
    }
  ]
}
```

| 필드 | 타입 | 설명 |
|---|---|---|
| `userId` | `Long` | 주문 사용자 ID |
| `items` | `List` | 주문 메뉴 목록 |
| `items[].menuId` | `Long` | 주문할 메뉴 ID |
| `items[].quantity` | `Integer` | 주문 수량 |

### Response

**200 OK**

```json
{
  "orderId": 100,
  "userId": 1,
  "totalPrice": 10000,
  "items": [
    {
      "menuId": 1,
      "menuName": "아메리카노",
      "menuPrice": 3000,
      "quantity": 2
    },
    {
      "menuId": 2,
      "menuName": "카페라떼",
      "menuPrice": 4000,
      "quantity": 1
    }
  ],
  "orderedAt": "2026-10-01T23:00:00"
}
```

| 필드 | 타입 | 설명 |
|---|---|---|
| `orderId` | `Long` | 주문 ID |
| `userId` | `Long` | 주문 사용자 ID |
| `totalPrice` | `Long` | 주문 총 금액 |
| `remainingPoint` | `Long` | 결제 후 잔여 포인트 |
| `items` | `List` | 주문 메뉴 목록 |
| `items[].menuId` | `Long` | 메뉴 ID |
| `items[].menuName` | `String` | 주문 당시 메뉴 이름 |
| `items[].menuPrice` | `Long` | 주문 당시 메뉴 가격 |
| `items[].quantity` | `Integer` | 주문 수량 |
| `orderedAt` | `LocalDateTime` | 주문 일시 |

### 주요 처리 규칙

- 존재하지 않는 사용자는 주문할 수 없습니다.
- 존재하지 않는 메뉴는 주문할 수 없습니다.
- 주문 수량보다 재고가 부족하면 주문할 수 없습니다.
- 주문 금액보다 보유 포인트가 부족하면 주문할 수 없습니다.
- 결제 성공 시 사용자의 포인트가 차감됩니다.
- 주문 성공 시 메뉴 재고가 차감됩니다.
- 주문 당시의 메뉴 이름과 가격을 `OrderItem`에 저장합니다.
- 주문 완료 이벤트를 외부 데이터 수집 플랫폼으로 전달합니다.
- 동일한 요청이 중복 처리되지 않도록 멱등성을 고려합니다.

### 주요 예외

- 존재하지 않는 사용자
- 존재하지 않는 메뉴
- 재고 부족
- 포인트 부족
- 중복 요청
- 주문 처리 중 데이터베이스 오류

---

## 4. 인기 메뉴 조회

### 기본 정보

- **Method**: `GET`
- **URL**: `/api/menus/rank`
- **설명**: 최근 7일 동안 주문된 메뉴를 기준으로 인기 메뉴 TOP 3를 조회합니다.

### 인기 메뉴 집계 기준

- 최근 7일 동안 주문된 **주문 건수**를 기준으로 집계합니다.
- 동일한 주문에서 같은 메뉴를 여러 개 주문하더라도 주문 건수는 1건으로 계산합니다.
- 메뉴별 주문 건수가 높은 순으로 정렬합니다.
- 주문 건수가 동일한 경우 메뉴 가격이 높은 순으로 정렬합니다.
- 주문 건수와 가격이 모두 동일한 경우 메뉴 ID가 낮은 순으로 정렬합니다.
- 상위 3개의 메뉴만 반환합니다.

### Response

**200 OK**

```json
{
  "menus": [
    {
      "menuId": 1,
      "name": "아메리카노",
      "orderCount": 152
    },
    {
      "menuId": 2,
      "name": "카페라떼",
      "orderCount": 98
    },
    {
      "menuId": 3,
      "name": "바닐라라떼",
      "orderCount": 76
    }
  ]
}
```

| 필드 | 타입 | 설명 |
|---|---|---|
| `menus` | `List` | 인기 메뉴 목록 |
| `menus[].menuId` | `Long` | 메뉴 ID |
| `menus[].name` | `String` | 메뉴 이름 |
| `menus[].orderCount` | `Long` | 최근 7일간 해당 메뉴가 포함된 주문 건수 |

### 인기 메뉴 집계 처리

주문 완료 이벤트를 Kafka를 통해 전달하고, Consumer가 Redis에 인기 메뉴 집계 데이터를 갱신합니다.

```text
주문 완료
   ↓
주문 이벤트 발행
   ↓
Kafka
   ↓
Consumer
   ↓
Redis 인기 메뉴 집계 갱신
   ↓
인기 메뉴 조회
```