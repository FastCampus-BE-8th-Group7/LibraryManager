# LibraryManager
도서관 관리 시스템을 구현하는 레포지토리입니다.

# 커밋 메시지

## 커밋 메시지 규칙

>  [참고한 사이트](https://velog.io/@chojs28/Git-%EC%BB%A4%EB%B0%8B-%EB%A9%94%EC%8B%9C%EC%A7%80-%EA%B7%9C%EC%B9%99)

1. 제목과 본문, 바닥글을 **빈 행으로 구분**한다.
2. 제목은 **50글자** 이내로 제한한다.
3. 제목 끝에는 **마침표를 넣지 않는다**.
4. 제목은 **명령문**으로 사용하며 **과거형을 사용하지 않는다**.
5. 본문의 **각 행은 72글자 내**로 제한한다.
6. 어떻게 보다는 **무엇과 왜를 설명**한다.

## 커밋 메시지 구조

```tsx
타입: 주제(제목) // Header(헤더)
// Header, Body, Footer는 빈 행으로 구분한다.
본문 // Body(바디)
// Header, Body, Footer는 빈 행으로 구분한다.
바닥글 // Footer
```

1. **Header**: **필수**로 커밋의 핵심적인 내용을 적어야 한다.
    - **타입**은 해당 커밋의 성격을 나타내며 아래 중 하나여야 한다.

| 타입 이름 | 내용                                                  |
| --------- | ----------------------------------------------------- |
| feat      | 새로운 기능에 대한 커밋                               |
| fix       | 버그 수정에 대한 커밋                                 |
| build     | 빌드 관련 파일 수정 / 모듈 설치 또는 삭제에 대한 커밋 |
| chore     | 그 외 자잘한 수정에 대한 커밋                         |
| ci        | ci 관련 설정 수정에 대한 커밋                         |
| docs      | 문서 수정에 대한 커밋                                 |
| style     | 코드 스타일 혹은 포맷 등에 관한 커밋                  |
| refactor  | 코드 리팩토링에 대한 커밋                             |
| test      | 테스트 코드 수정에 대한 커밋                          |
| perf      | 성능 개선에 대한 커밋                                 |

2. **Body**: **생략가능**하고 Header에서 표현할 수 없는 **상세한 내용**을 적는다.

3. **Footer**: **생략가능**하고 어떤 이슈에서 왔는지 같은 **참조 정보들을 추가**하는 용도로 사용한다.

## 작성 예시

```tsx
git commit -m "fix: Safari에서 모달을 띄웠을 때 스크롤 이슈 수정

모바일 사파리에서 Carousel 모달을 띄웠을 때,
모달 밖의 상하 스크롤이 움직이는 이슈 수정.

issues: #1137
```

