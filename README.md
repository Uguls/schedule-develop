# 📘 API 명세서

## 공통 정보

- 요청/응답 형식: `application/json`
- 인증 방식: Cookie + Session (인증 필요 API에 한함)
- 공통 응답 예외 형식:

```json
{
  "timestamp": "2025-03-27T14:26:45",
  "status": 400,
  "error": "BAD_REQUEST",
  "code": "C001",
  "message": "잘못된 입력값입니다",
  "path": "/api/login",
  "fieldErrors": [
    {
      "field": "username",
      "message": "아이디 입력은 필수입니다"
    }
  ]
}
```

---

## ✅ [POST] /schedules (일정 등록)

### ✔ 요청 바디

```json
{
  "title": "공부하기",
  "description": "자바 복습",
  "userId": 1
}
```

### ✔ 응답 바디

```json
{
  "scheduleId": 10,
  "message": "일정이 등록되었습니다"
}
```

---

## ✅ [GET] /schedules/{id} (일정 단건 조회)

### ✔ 응답 바디

```json
{
  "scheduleId": 10,
  "title": "공부하기",
  "description": "자바 복습",
  "username": "Hyungwoo"
}
```

---

## ✅ [PUT] /schedules/{id} (일정 수정)

### ✔ 요청 바디

```json
{
  "title": "스터디하기",
  "description": "스프링 핵심 복습"
}
```

### ✔ 응답 바디

```json
{
  "message": "일정이 수정되었습니다"
}
```

---

## ✅ [DELETE] /schedules/{id} (일정 삭제)

### ✔ 응답 바디

```json
{
  "message": "일정이 삭제되었습니다"
}
```
---
