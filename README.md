# 🫧WashPang
**Washpang: 세탁의 혁신을 경험하세요!🫧**

Washpang은 당신의 소중한 시간을 아껴주는 세탁 홈서비스 웹앱입니다. 간편하게 세탁물을 신청하고, 문앞에 두기만 하면 전문 세탁업체가 직접 수거하여 세탁 후 다시 집앞까지 배달해드립니다. 
번거로운 세탁 과정을 없애고, 효율적이고 편리한 서비스를 통해 일상 속 여유를 찾으세요. 
Washpang과 함께라면 세탁이 더 이상 귀찮은 일이 아닙니다!

<br>

Kotlin으로 리펙토링 -> [Refactoring Repository](https://github.com/prgrms-be-devcourse/NBE2-3-3-team10)

# 📖
<img src="https://github.com/user-attachments/assets/d5cdc9ce-69f8-4f61-b440-43b0f295a6b5" width="200" height="400" />
<br>

## 프로젝트 소개

- 워시팡은 홈서비스를 기반으로 세탁 서비스를 중개하는 웹 앱입니다.
- 고객들에게 사용자 맞춤형으로 동네 세탁소를 매칭 및 실시간 예약 서비스를 제공합니다.
- 사장님들에게 새로운 고객층을 확보해주고 예약 및 상태 관리를 지원해줍니다.
- C2C를 기반으로 홈 서비스를 중개해줍니다.
- **Target User** : 세탁소 운영자, 자취생, 주부 등 바쁜 현대인을 타겟으로 합니다.

<br>

## 팀원 구성



|                                                                      **문건웅**                                                                      |                                                                **송혜원**                                                                |                                                              **이종찬**                                                               |                                                                  **오현식**                                                                  |                                                               **진세현**                                                                |
|:-------------------------------------------------------------------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------:|
| [<img src="https://avatars.githubusercontent.com/u/129466239?v=4" height=150 width=150> <br/> @moon-kun-woong](https://github.com/moon-kun-woong) | [<img src="https://avatars.githubusercontent.com/u/130907813?v=4" height=150 width=150> <br/> @Hyeromon](https://github.com/Hyeromon) | [<img src="https://avatars.githubusercontent.com/u/50389081?v=4" height=150 width=150> <br/> @LeeVell](https://github.com/LeeVell) | [<img src="https://avatars.githubusercontent.com/u/100890581?v=4" height=150 width=150> <br/> @HYEONSIKOH](https://github.com/HYEONSIKOH) | [<img src="https://avatars.githubusercontent.com/u/75302306?v=4" height=150 width=150> <br/> @niki8533](https://github.com/niki8533) |
|                                                                프론트 담당 <br> 업체 페이지                                                                 |                                                        세탁 이용 신청 <br> 이용 내역 페이지                                                        |                                           일반 회원가입 및 로그인 <br> JWT 인증/인가 <br> DFD에 맞게 리팩토링                                           |                                          회원가입 & 카카오 소셜 로그인 <br> 카카오페이 결제 <br> Redis를 이용한 데이터 관리                                           |                                                    세탁소 등록 <br> 세탁소 목록 조회(맵/카테고리)                                                     |


<br>

## 1. 개발 환경

- Front : JSP -> Thymeleaf (리펙토링)
- Back-end : SpringBoot, MariaDB, JPA, Redis, Spring Security
- API : 카카오맵 API, 카카오톡 API, 카카오페이 API, Swwager API
- 버전 및 이슈관리 : Github, Github Issues, Github Project
- 협업 툴 : Discord, Notion, Github, Slack
- 기획 : [Figma](https://www.figma.com/design/zaPwvGTyXmiWPWCydbRmIR/2%EC%B0%A8%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8?node-id=0-1&t=yebkxSL4q1VBDs6r-1)
- ERD :  [Db Diagram](https://dbdiagram.io/d/675aa7f746c15ed47925f072)
- 발표자료: [Canva](https://www.canva.com/design/DAGbAjzpRE4/aYPwH5zjWi2Bq1VwC89wsA/edit)
  <br>

## 2. 채택한 개발 기술과 브랜치 전략

### Redis

- Redis는 메모리 기반의 Key-Value 구조 데이터 저장소로, 세션 관리, 캐싱, 메시지 브로커 등 다양한 용도로 사용할 수 있습니다.
- **Redis 사용 방식**
    - RefreshToken을 RTR 방식으로 관리하기 위해 사용했습니다.
    - RefreshToken을 Redis에 저장하고 쿠키에 있는 RefreshToken과 같은지 비교하여 사용자의 인증을 처리했습니다.
    - RefreshToken이 만료되지 않는 이상, 페이지 전환마다 AccessToken을 재발급하고 이와 동시에 RefreshToken도 재발급했습니다.
    - Tid 값을 Redis에서 관리하여 결제 요청 및 결제 승인 시 사용했습니다.
- **Redis 사용 이유**
    - Redis는 메모리 기반의 데이터 저장소로 데이터를 빠르게 처리할 수 있어 성능이 우수했습니다.
    - 또한 데이터를 Key-Value 구조로 저장하기 때문에 데이터를 쉽게 관리할 수 있었습니다.

### Thymeleaf

- 기존 다른 프론트엔드 템플릿에서는 HTML과 Java 코드를 혼합해 작성해야했습니다. 따라서 순수 HTML로 작성할 수 있는 Thymeleaf를 사용하기로 했습니다
- JSP가 아닌 Thymeleaf를 채택한 이유
    - Thymeleaf는 Spring Boot에서 기본 제공되는 템플릿 엔진으로 설정이 간편했습니다
    - 또한 조건문, 텍스트 처리 등 다양한 기능을 지원하며 별도의 JSTL을 추가할 필요가 없어 코드가 간결하고 읽기 쉽게 유지할 수 있었습니다
- 활용 방식 뭐라고 쓰지 흠......,,..,/,.//.,/.,.,//./.,/.,

### 브랜치 전략

#### 📋 Commit Message Convention 📋

| Tag | Description |
| --- | --- |
| `Feat` | 새로운 기능 추가 |
| `Fix` | 버그 수정 |
| `Docs` | 문서 추가, 수정, 삭제 |
| `Test` | 테스트 코드 추가, 수정, 삭제 |
| `Style` | 코드 형식 변경 |
| `Refactor` | 코드 리팩토링 |
| `Perf` | 성능 개선 |
| `Build` | 빌드 관련 변경사항 |
| `Ci` | CI 관련 설정 수정 |
| `Chore` | 기타 변경사항 |


### 🌳 브랜치 네이밍 규칙

**[tag]/[category]/[number] 이렇게 작성해주세요**

feat/customer/1

feat/laundry/1

feat/review/1


<br>

## 3. 프로젝트 구조

```
src
 └── main
     └── java
         └── org
             └── team10
                 └── washcode
                     ├── controller              // 전역 컨트롤러: 전체 애플리케이션에서 공통적으로 사용되는 컨트롤러를 관리
                     ├── domain                  // 주요 도메인 관련 파일을 그룹화
                     │   ├── admin               // 관리자와 관련된 기능을 관리
                     │   ├── handledItems        // 처리 가능한 항목 관련 기능을 관리
                     │   ├── inquiry             // 문의와 관련된 기능을 관리
                     │   └── laundryshop         // 세탁소 관련 기능의 주요 도메인
                     │       ├── controller      // 세탁소 관련 요청을 처리하는 컨트롤러
                     │       ├── dto             // 세탁소 관련 데이터 전송 객체 (DTO)
                     │       ├── entity          // 세탁소 관련 엔티티 클래스
                     │       ├── exception       // 세탁소에서 발생하는 예외 처리
                     │       ├── repository      // 세탁소 데이터 접근 계층 (Repository)
                     │       └── service         // 세탁소 관련 비즈니스 로직을 관리
                     ├── order                   // 주문과 관련된 기능을 관리
                     ├── pickup                  // 픽업과 관련된 기능을 관리
                     ├── review                  // 리뷰 관련 기능을 관리
                     └── user                    // 사용자 관련 기능을 관리
                     └── global                  // 애플리케이션 전역적인 공통 요소를 관리
                         ├── auth                // 인증 관련 로직을 관리
                         │   └── comm
                         │       ├── enums       // 인증 관련 열거형 정의
                         │       ├── exception   // 인증에서 발생하는 예외 처리
                         ├── config              // 설정 파일을 관리
                         └── oauth2
                             └── client          // OAuth2 클라이언트 관련 기능 관리

```

<br>

## 4. 역할 분담

### 예시)

- **UI**
  - 페이지 : 홈, 검색, 게시글 작성, 게시글 수정, 게시글 상세, 채팅방
  - 공통 컴포넌트 : 게시글 템플릿, 버튼
- **기능**
  - 유저 검색, 게시글 등록 및 수정, 게시글 상세 확인, 댓글 등록, 팔로워 게시글 불러오기, 좋아요 기능

### 👑  문건웅

- **기능**

<br>

### 👻 송혜원

- **기능**
  - 세탁 신청 페이지 제작
  - 신청 내역 및 상세 페이지 제작
  - 결제 내역 및 상세 페이지 제작
    - 결제 내역을 한눈에 확인할 수 있으며, 1개월, 3개월, 6개월 필터를 사용하여 특정 기간 내 결제했던 내역을 쉽게 찾아볼 수 있도록 설계
<br>

### 😎 오현식

- **기능**
  - 회원가입 기능 제작 
    1. (Email 중복확인, 카카오 소셜 로그인을 통한 회원가입)
  - 카카오 소셜 로그인 제작
    1. **RestTemplate** 사용
  - Redis를 이용해서 RefreshToken을 RTR 방식으로 관리<br>
    1. RefreshToken을 Redis에 저장 & 쿠키에 있는 RefreshToken과 같은지 비교 <br>
    2. RefreshToken이 만료되지 않는 이상, 페이지 전환마다 AccessToken을 재발급
    3. 이와 동시에 RefreshToken도 재발급
  - 카카오페이 결제 기능 제작 <br>
    1. 결제 요청 & 결제 승인 기능 제작
    2. Tid 값을 Redis에서 관리

<br>

### 🐬 이종찬

- **기능**

<br>

### ❄️ 진세현

- **기능**
  - 세탁 카테고리 별 세탁소 목록 조회
  - 현재 위치 주변에 있는 세탁소 지도로 조회
  - 세탁소 상세 내역 조회
  - 세탁소 정보 등록 및 수정

<br>

## 5. 개발 기간 및 작업 관리

### 개발 기간

- **전체 개발 기간** : 2024.12.10 ~ 2025.01.05
- **주제 선정 및 기획** : 12.10 - 12.12
- **UI 구현** : 12.12 - 12.15
- **기능명세서 작성 및 역할 분배** : 12.16 - 12.17
- **기능 구현** : 12.18 - 01.03
- **마무리 및 발표준비** : 01.03-05

<br>

### 작업 관리

- GitHub Projects와 Issues를 사용하여 진행 상황을 공유했습니다.
- 주간회의를 진행하며 작업 순서와 방향성에 대한 고민을 나누고 Notion에 회의 내용을 기록했습니다.

<img src="https://github.com/user-attachments/assets/aaf15a94-7ef2-4eef-bd93-cecd71eefd78"/>
<br>


## 6. 페이지별 기능
<img src= "https://github.com/user-attachments/assets/f9bda4be-f474-41c3-925e-df77e1bbc6f6" height="380"/>

<br>
<br>

### 공통 (global)

#### [회원가입]
- 이메일이 있는지 없는지 확인하는 **중복확인** 버튼
- 핸드폰 인증은 아직 **미구현**
- 카카오 회원은 가입이 안되어 있다면 카카오연동 이메일과 이름이 자동 입력
- 사용자는 고객인지 상점 관리자인지 선택

| 회원가입 (일반)                                                                                                             | 회원가입 (카카오 회원)                                                                                                         |
|-----------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------|
| <img width="210" alt="Image" src="https://github.com/user-attachments/assets/f9e3a869-3866-4912-9d30-981ea7a5c3d3" /> | <img width="210" alt="Image" src="https://github.com/user-attachments/assets/9004e5e1-93ef-4111-8ae1-b74b8a39ebd5" /> |

<br>

#### [로그인]
- 아이디와 비밀번호를 직접 입력하여 로그인
- 카카오 회원은 버튼을 누른 후, 로그인
- 로그인 성공 시, Role에 맞게 자동 페이지 이동

| 로그인                                                                                                                   |
|-----------------------------------------------------------------------------------------------------------------------|
| <img width="210" alt="Image" src="https://github.com/user-attachments/assets/51a5cfef-e6e7-407c-ba6e-4fbca5c6ef6c" /> |

<br>

#### [회원정보 수정]
- 사용자는 자신의 정보를 수정할 수 있습니다.

| 내 정보                                                                                                                  | 내 정보 확인  |내 정보 수정                                                                                                               |
|-----------------------------------------------------------------------------------------------------------------------|----------|-----------------------------------------------------------------------------------------------------------------------|
| <img width="210" alt="Image" src="https://github.com/user-attachments/assets/9bbefcbd-1c2b-4c64-83d1-8c14628adcdc" /> |  <img width="210" alt="Image" src="https://github.com/user-attachments/assets/8729cb81-61f4-4ee4-9b95-73ff790c2882" />           |<img width="210" alt="Image" src="https://github.com/user-attachments/assets/82876df9-4406-4bbd-ba5c-757987d60020" /> |

<br>
<br>

###  고객 (Customer)
#### [메인]
- 간단하게 내 현재 배송 주소를 보여줍니다
- 내 주변 세탁소 찾기를 누르면 지도와 함께 근처 세탁소가 표시됩니다.
- 각 카테고리를 클릭하면 지원하는 주변 세탁소 목록을 보수 있습니다.

| 홈 화면 | 
|------|
|<img width="210" alt="Image" src="https://github.com/user-attachments/assets/fc2e7f4f-2d3c-4a5e-ad2f-476086dcf27f" />|

<br>

#### [검색]
- 근처 세탁소 목록이 나옵니다
- 세탁소 이름으로도 검색할 수 있습니다
- 지역명으로도 검색할 수 있습니다

| 검색 (지도)                                                                                                   |
|-----------------------------------------------------------------------------------------------------------|
| <img src= "https://github.com/user-attachments/assets/c671b034-b339-4b5c-b840-bc2ca883b856" width="210"/> |

<br>

#### 세탁소 상세 페이지
| 세탁소 상세 페이지                                                                                                            |
|-----------------------------------------------------------------------------------------------------------------------|
| <img width="210" alt="Image" src="https://github.com/user-attachments/assets/4084255b-8a4c-4362-a120-4a1e8e29c3f7" /> |

<br>

#### 1. 카테고리별 세탁소 목록

| 카테고리                                                                                            | 카테고리 별 세탁소 목록                                                                                 |
|-------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|
| <img  src ="https://github.com/user-attachments/assets/40c6210f-9a20-45da-9a03-e847deeb9026" width="210"/> | <img src="https://github.com/user-attachments/assets/c7552ab0-a733-428f-acfe-bfe3d4613ca6" width="210"/> |
<br>

### [세탁물 이용신청]

#### 1. 이용신청서 작성

| 이용신청                                                                                                                  |
|-----------------------------------------------------------------------------------------------------------------------|
| <img width="210" alt="Image" src="https://github.com/user-attachments/assets/a530a3a1-b0ac-493c-8812-ff276ae48641" /> |
<br>

#### 2. 이용신청 내역


| 이용 신청 내역 |
|----------|
|<img width="210" alt="Image" src="https://github.com/user-attachments/assets/78d99be9-d762-400a-a574-0ce06f7c045b" />|

<br>

#### 3. 이용신청 상세 내역

| 이용 신청 상세 내역 |
|-------------|
|<img width="210" alt="Image" src="https://github.com/user-attachments/assets/62441fbe-5a7c-44b8-998c-2bf75d5eaf70" />|

<br>

### [세탁소]

#### 1. 세탁소 등록
- 업체명, 전화번호, 대표자명, 주소, 사업자번호, 휴업일, 상품정보를 등록합니다.
- 대표자명과 사용자 이름은 다를 수 있습니다.

| 세탁소 등록                                                                                                                |
|-----------------------------------------------------------------------------------------------------------------------|
| <img width="210" alt="Image" src="https://github.com/user-attachments/assets/4b61b6a8-1dad-4469-8eba-b4d7a3e41efc" /> |

<br>

#### 2. 주문내역(세탁요청)
| 세탁 요청                                                                                                                 | 세탁 요청 상세 |
|-----------------------------------------------------------------------------------------------------------------------|----------|
| <img width="210" alt="Image" src="https://github.com/user-attachments/assets/5e6efd22-8f83-436d-83b1-c78c370153d9" /> | <img width="210" alt="Image" src="https://github.com/user-attachments/assets/69401d38-740d-40d9-a282-0ba8f4f2f8a3" />         |

<br>

#### 3. 매출관리

| 매출 관리                                                                                                                 |
|-----------------------------------------------------------------------------------------------------------------------|
| <img width="210" alt="Image" src="https://github.com/user-attachments/assets/f2001022-dc6d-4e7a-b6ef-e89992b1341a" /> |

<br>

#### 4. 수거요청

| 수거 요청 |
|-------|
|<img width="210" alt="Image" src="https://github.com/user-attachments/assets/988b3694-7dff-42bd-94b6-9d05daff5232" />|

#### 5. 배달요청

| 배달 요청 |
|-------|
|<img width="210" alt="Image" src="https://github.com/user-attachments/assets/c8316eb8-4d40-47b9-80f8-6c76734f9bdb" />|
<br>


## 7. KPT 회고

### 👍 Keep
- **협업 방식**
  - 팀원 간 원활한 커뮤니케이션을 통해 문제 해결 속도를 높임.
  - Git을 통한 브랜치 전략으로 버전 관리와 협업의 체계성을 확보.

- **기술**
  - REST API 구현.
  - 다양한 API 사용 (카카오 맵, 결제 API 등).
  - Redis 및 Spring Security 활용.

<br>

### 📚 Problem
- **유지 보수 관점 문제**
  - 도메인 기준으로 패키지 분리 필요.
  - 패키지 및 함수 명칭의 비일관성 문제.

- **기능 관점 문제**
  - 업체에서 주문 상태 변경을 즉시 확인할 수 없음 → 고객 주문 요청/취소 시 알림 수단 필요.
  - 테스트 코드 작성이 미흡하여 안정성 확보에 리스크 존재.

<br>

### 🛠️ Try
- 어드민 페이지 제작.
- 패키지 리팩토링 (네이밍 및 구조 개선).
- 핸드폰 인증번호 확인 기능 추가.
- 이메일 및 카카오톡을 통한 주문 상태 변경 시 업체에 실시간 알림 전송.
- **JWT(인증)** 와 **SECURITY(인가)** 혼용을 위한 커스텀 필터 제작.
- 핵심 기능에 대한 테스트 코드 작성으로 커버리지 강화.
- 커스텀 Exception 제작.