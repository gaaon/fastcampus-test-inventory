# 재고 서비스 rest api 설계
## 공통
- 도메인: http://localhost:8080

## 기능
### 1. 재고 조회

#### 요청

```http request
GET /api/v1/inventory/{itemId}
```

#### 응답

정상

```http
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "data": {
    "item_id": "{itemId}",
    "stock": {stock}
  }
}
```

상품이 존재하지 않을 경우
```http
HTTP/1.1 404 Not Found
Content-Type: application/json;charset=UTF-8

{
  "error": {
    "local_message": "자산이 존재하지 않습니다",
    "code": 1000
  }
}
```

### 2. 재고 차감

#### 요청

```http request
POST /api/v1/inventory/{itemId}/decrease
Content-Type: application/json;charset=UTF-8

{
  "quantity": {quantity}
}
```

#### 응답

정상
```http request
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "data": {
    "item_id": "{itemId}",
    "stock": {stock}
  }
}
```

상품이 존재하지 않을 경우
```http
HTTP/1.1 404 Not Found
Content-Type: application/json;charset=UTF-8

{
  "error": {
    "local_message": "자산이 존재하지 않습니다",
    "code": 1000
  }
}
```

재고가 부족할 경우
```http
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "error": {
    "local_message": "재고가 부족합니다",
    "code": 1001
  }
}
```

차감 수량이 음수일 경우
```http
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "error": {
    "local_message": "차감 수량이 유효하지 않습니다",
    "code": 1002
  }
}
```

### 3. 재고 수정

#### 요청

```http request
PATCH /api/v1/inventory/{itemId}/stock
Content-Type: application/json;charset=UTF-8

{
  "stock": {stock}
}
```

#### 응답

정상
```http request
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "data": {
    "item_id": "{itemId}",
    "stock": {stock}
  }
}
```

상품이 존재하지 않을 경우
```http
HTTP/1.1 404 Not Found
Content-Type: application/json;charset=UTF-8

{
  "error": {
    "local_message": "자산이 존재하지 않습니다",
    "code": 1000
  }
}
```

수정하려는 재고가 음수인 경우
```http
HTTP/1.1 400 Bad Request
Content-Type: application/json;charset=UTF-8

{
  "error": {
    "local_message": "재고가 유효하지 않습니다",
    "code": 1003
  }
}
```


## 참고
- [재고 서비스 prd v1](../prd/재고서비스_prd_v1.md)
- [재고 서비스 techspec v1](../techspec/재고서비스_techspec_v1.md)
```