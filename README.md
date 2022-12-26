# EmployeeManagementProgram
관리자 계정을 로그인하여 DB에 등록된 사원 정보를 조작하는 CRUD 프로그램

## 제작 기간
2022.09 ~ 2022.12

## 제작 기술
- Java Swing
- MySQL

## 주요 기능
### 1. 로그인
- DB의 관리자 테이블의 정보를 읽어와 로그인
- 관리자 테이블의 정보와 다를 시 로그인 실패
- 프로그램 종료 버튼

### 2. 사원 정보
- DB의 사원 테이블의 번호, 이름, 생년월일, 주소, 이메일, 휴대전화, 직위 수정, 삭제
- 프로필 이미지 수정, 삭제 (미구현)
- 사원의 번호, 이름을 테이블에 표시하고 테이블 행 클릭 시 좌측 텍스트 필드에 해당 사원의 정보가 표시
- 사원의 번호, 이름을 필터링 및 조회하여 테이블에 표시
- 테이블에 전체 사원 정보를 다시 표시하고 좌측 텍스트 필드를 초기화하는 새로고침 버튼

### 3. 사원 등록
- 이름, 생년월일, 주소, 이메일, 휴대전화, 직위를 입력하여 사원 등록
- 번호는 자동 넘버링되어 입력 불필요
- 프로필 이미지 추가 (미구현)
- 텍스트 필드를 초기화하는 다시 작성 버튼

### 4. 파일
- 관리자 계정 로그아웃
- 프로그램 종료

### 5. 도움말
- 제작자 및 저작권 표기 창


## 이미지

> 로그인

![login](https://user-images.githubusercontent.com/113095585/198043360-9e99b3c6-79b7-46e1-8507-9267558a6af2.png)

> 사원 정보

![사원 정보 패널](https://user-images.githubusercontent.com/113095585/206369394-5a0cba3a-ac73-4c91-863e-2851fe2626f1.png)

> 사원 등록

![signup](https://user-images.githubusercontent.com/113095585/198043392-4360fb01-c39a-4dd5-a84b-6652c7131654.png)
