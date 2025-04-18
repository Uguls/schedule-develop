# 📘 일정 관리 API 명세서

---

## ✅ User API (/users)

### 🔹 [POST] `/users/signup` - 회원가입

#### ✔ 요청 바디
```json
{
  "name": "박형우",
  "email": "test@test.com",
  "password": "123456"
}
```

#### ✔ 응답 바디
```json
{
  "name": "박형우",
  "email": "test@test.com",
  "id": 1,
  "created_date": "2025-04-02T07:05:04.386Z",
  "updated_date": "2025-04-02T07:05:04.386Z"
}
```

---

### 🔹 [POST] `/users/login` - 로그인

#### ✔ 요청 바디
```json
{
  "email": "test@test.com",
  "password": "123456"
}
```

#### ✔ 응답 바디
```json
{
  "name": "박형우",
  "email": "test@test.com",
  "id": 1,
  "created_date": "2025-04-02T07:05:04.386Z",
  "updated_date": "2025-04-02T07:05:04.386Z"
}
```

---

### 🔹 [POST] `/users/logout` - 로그아웃

#### ✔ 응답
`200 OK`
```text
로그아웃 완료
```

---

### 🔹 [GET] `/users/{id}` - 특정 유저 조회

#### ✔ 요청 경로
```
/users/1
```

#### ✔ 응답 바디
```json
{
  "name": "박형우",
  "email": "test@test.com",
  "id": 1,
  "created_date": "2025-04-02T07:05:04.386Z",
  "updated_date": "2025-04-02T07:05:04.386Z"
}
```

---

### 🔹 [PATCH] `/users/{id}` - 유저 정보 수정

#### ✔ 요청 경로
```
/users/1
```

#### ✔ 요청 바디
```json
{
  "name": "박형우",
  "email": "changedEmail@email.com",
  "password": "123456"
}
```

#### ✔ 응답 바디
```json
{
  "name": "박형우",
  "email": "changedEmail@email.com",
  "id": 1,
  "created_date": "2025-04-02T07:05:04.386Z",
  "updated_date": "2025-04-02T07:05:04.386Z"
}
```

---

### 🔹 [GET] `/users/` - 전체 유저 조회

#### ✔ 응답 바디
```json
[
  {
    "name": "박형우",
    "email": "changedEmail@email.com",
    "id": 1,
    "created_date": "2025-04-02T07:05:04.386Z",
    "updated_date": "2025-04-02T07:05:04.386Z"
  }
]
```

---

### 🔹 [DELETE] `/users/` - 로그인 유저 삭제

#### ✔ 응답
`200 OK`
```text
박형우 유저 삭제 성공
```

---

## ✅ Schedule API (/schedules)

### 🔹 [GET] `/schedules` - 전체 일정 조회 (페이징)

#### ✔ 요청 쿼리 파라미터
```
/schedules?page=0&size=10
```

#### ✔ 응답 바디
```json
[
  {
    "id": 1,
    "title": "공부하기",
    "description": "JPA 공부하기",
    "username": "박형우",
    "createdAt": "2025-04-02T07:03:41.403Z",
    "modifiedAt": "2025-04-02T07:03:41.403Z",
    "commentCount": 2
  }
]
```

---

### 🔹 [POST] `/schedules` - 일정 등록

#### ✔ 요청 바디
```json
{
  "title": "공부하기",
  "content": "JPA 공부하기"
}
```

#### ✔ 응답 바디
```json
{
  "title": "공부하기",
  "content": "JPA 공부하기",
  "user_name": "박형우",
  "created_date": "2025-04-02T07:03:41.403Z",
  "updated_date": "2025-04-02T07:03:41.403Z"
}
```

---

### 🔹 [GET] `/schedules/{id}` - 일정 상세 조회

#### ✔ 요청 경로
```
/schedules/1
```

#### ✔ 응답 바디
```json
{
  "title": "공부하기",
  "content": "JPA 공부하기",
  "user_name": "박형우",
  "created_date": "2025-04-02T07:03:41.403Z",
  "updated_date": "2025-04-02T07:03:41.403Z"
}
```

---

### 🔹 [PATCH] `/schedules/{id}` - 일정 수정

#### ✔ 요청 경로
```
/schedules/1
```

#### ✔ 요청 바디
```json
{
  "title": "수정된 제목",
  "content": "수정된 내용"
}
```

#### ✔ 응답 바디
```json
{
  "title": "수정된 제목",
  "content": "수정된 내용",
  "user_name": "박형우",
  "created_date": "2025-04-02T07:03:41.403Z",
  "updated_date": "2025-04-02T07:10:00.000Z"
}
```

---

### 🔹 [DELETE] `/schedules/{id}` - 일정 삭제

#### ✔ 요청 경로
```
/schedules/1
```

#### ✔ 응답
`200 OK`
```text
일정 삭제 성공
```

---

## ✅ Comment API (/comments)

### 🔹 [GET] `/comments` - 전체 댓글 조회

#### ✔ 응답 바디
```json
[
  {
    "content": "좋은 일정이네요!",
    "user_name": "박형우",
    "schedule_name": "공부하기",
    "created_date": "2025-04-02T07:11:22.000Z",
    "updated_date": "2025-04-02T07:11:22.000Z"
  }
]
```

---

### 🔹 [GET] `/schedules/{scheduleId}/comments` - 댓글 조회

#### ✔ 요청 경로
```
/schedules/1/comments
```

#### ✔ 응답 바디
```json
[
  {
    "content": "좋은 일정이네요!",
    "user_name": "박형우",
    "schedule_name": "공부하기",
    "created_date": "2025-04-02T07:11:22.000Z",
    "updated_date": "2025-04-02T07:11:22.000Z"
  }
]

```

---

### 🔹 [POST] `/comments` - 댓글 작성

#### ✔ 요청 바디
```json
{
  "scheduleId": 1,
  "userId": 1,
  "content": "좋은 일정이네요!"
}
```

#### ✔ 응답 바디
```json
{
  "content": "좋은 일정이네요!",
  "user_name": "박형우",
  "schedule_name": "공부하기",
  "created_date": "2025-04-02T07:11:22.000Z",
  "updated_date": "2025-04-02T07:11:22.000Z"
}
```

---

### 🔹 [PATCH] `/comments/{id}` - 댓글 수정

#### ✔ 요청 경로
```
/comments/1
```

#### ✔ 요청 바디
```json
{
  "content": "수정된 댓글입니다."
}
```

#### ✔ 응답 바디
```json
{
  "content": "수정된 댓글입니다.",
  "user_name": "박형우",
  "schedule_name": "공부하기",
  "created_date": "2025-04-02T07:11:22.000Z",
  "updated_date": "2025-04-02T07:15:00.000Z"
}
```

---

### 🔹 [DELETE] `/comments/{id}` - 댓글 삭제

#### ✔ 요청 경로
```
/comments/1
```

#### ✔ 응답
`200 OK`
```text
댓글 삭제 성공
```

