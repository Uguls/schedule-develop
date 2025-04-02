# 📘 API 명세서

---

## ✅ /users (유저)

### ✅ [POST] /signup (회원가입)

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

### ✅ [POST] /login (로그인)

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
### ✅ [GET] /{id} (유저찾기)

#### ✔ 요청 파라미터
`/users/1`

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

### ✅ [PATCH] /{id} (유저 수정하기)

#### ✔ 요청 파라미터
`/users/1`

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

### ✅ [GET] / (모든 유저 조회하기)

#### ✔ 응답 바디

```json
[
  {
    "name": "박형우",
    "email": "changedEmail@email.com",
    "id": 1,
    "created_date": "2025-04-02T07:05:04.386Z",
    "updated_date": "2025-04-02T07:05:04.386Z"
  },
  {
    "name": "박형우2",
    "email": "changedEmail2@email.com",
    "id": 2,
    "created_date": "2025-04-02T07:05:04.386Z",
    "updated_date": "2025-04-02T07:05:04.386Z"
  }
]
```

### ✅ [DELETE] / (유저 삭제하기)

#### ✔ 요청 바디
```json
{
  "name": "박형우",
  "email": "changedEmail@email.com",
  "password": "123456"
}
```

#### ✔ 응답
`200 OK`

---

## ✅ /schedules (일정)

### ✅ [GET] / (일정 전체 조회 - 페이징)

#### ✔ 요청 파라미터 (Query)

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

### ✅ [GET] /{id} (일정 상세 조회)

#### ✔ 요청 파라미터
`/schedules/1`

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

### ✅ [PATCH] /{id} (일정 수정)

#### ✔ 요청 파라미터
`/schedules/1`

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

### ✅ [DELETE] /{id} (일정 삭제)

#### ✔ 요청 파라미터
`/schedules/1`

#### ✔ 응답
`200 OK`

---

## ✅ /comments (댓글)

### ✅ [GET] / (전체 댓글 목록)

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

### ✅ [GET] /{id} (댓글 단일 조회)

#### ✔ 요청 파라미터
`/comments/1`

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

### ✅ [POST] / (댓글 작성)

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

### ✅ [PATCH] /{id} (댓글 수정)

#### ✔ 요청 파라미터
`/comments/1`

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

### ✅ [DELETE] /{id} (댓글 삭제)

#### ✔ 요청 파라미터
`/comments/1`

#### ✔ 응답
`200 OK`

---

