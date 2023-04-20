# 구인/구직 사이트 프로젝트

## 기술 스택
- JDK 11
- SpringBoot 2.7.9
- MyBatis
- 테스트 h2 DB
- 프로덕션 MySQL DB
- JSP
---
## 모델링
### 1단계
- Employee(개인 유저)
- Employee_info(개인 유저 상세정보)
- Company(기업 유저)
- Resume(이력서)
- announcement(공고)
- support(지원자)
### 2단계
- Subscribe
### 3단계
- Admin
---
## 프로세스(02/26 ~ 03/04) - 일주일
### 1단계 **( ~ 02/28)**
- 개인 회원가입, 기업 회원가입, 로그인
- 개인 상세정보 등록, 개인 상세정보 보기, 개인 상세정보 수정하기
- 기업 소개서 등록, 기업 소개서 수정, 기업 상세보기
- 이력서 등록, 이력서 상세보기, 이력서 수정, 이력서 삭제
- 공고 등록하기, 공고 상세보기, 공고 수정하기, 공고 삭제하기
- 기업 지원하기(개인입장), 지원자 리스트 보기(기업입장)
### 2단계 **( ~ 03/01)**
- 검색, 썸네일, 썸네일, '경력사항, 자격증, 기술스택' 추가 등록(+)
### 3단계 **( ~ 03/02)**
- 구독하기, 구독취소, 구독한 목록 보기, 아이디 저장
### 4단계 **( ~ 03/03)**
- 아이디 중복체크, 페이징
### 5단계 **( ~ 03/04)**
- 합격, 불합격 통보
---
### REST 변경점
![image](https://user-images.githubusercontent.com/68271830/233276035-0f8ca00d-5ceb-4a89-9532-401896345411.png)

![image](https://user-images.githubusercontent.com/68271830/233276313-8a00afb5-5cd0-415a-a059-b7d6bbb5d599.png)

![image](https://user-images.githubusercontent.com/68271830/233276353-8de4475d-183c-4e0f-b485-37e22ebbf1a2.png)

![image](https://user-images.githubusercontent.com/68271830/233276401-c824197f-8b05-4c3b-98fd-5fa41feb3ede.png)

![image](https://user-images.githubusercontent.com/68271830/233276470-8331d9ad-f251-4821-8717-35f2f15f4b61.png)

![image](https://user-images.githubusercontent.com/68271830/233276538-5e396df9-6c18-4109-afd0-2ccc0a6b33c4.png)

![image](https://user-images.githubusercontent.com/68271830/233276574-fbe02855-b6b0-4095-b8b5-966bc6110d59.png)
---
### 사용자 시나리오
![image](https://user-images.githubusercontent.com/68271830/233270113-64f0b75b-89a0-4284-9f16-acf76ef3df45.png)

![image](https://user-images.githubusercontent.com/68271830/233270066-091f0fbe-d185-4520-83cb-c2270b63c236.png)

![image](https://user-images.githubusercontent.com/68271830/233269981-bb202074-75d2-4bae-b86f-c7c00d3f976e.png)

![image](https://user-images.githubusercontent.com/68271830/233270221-92f4984d-9062-47b0-b18d-0f1f64856a49.png)

![image](https://user-images.githubusercontent.com/68271830/233270272-010fcc3c-be07-45a1-aa35-c9a9c8520adc.png)

![image](https://user-images.githubusercontent.com/68271830/233270320-cb3748ec-4007-4d2b-801b-997be0e33ecf.png)

![image](https://user-images.githubusercontent.com/68271830/233270356-e26ecfae-f3d6-4db5-819b-075c66e88c16.png)

![image](https://user-images.githubusercontent.com/68271830/233270392-79ee312b-069e-4097-b11c-b7b0e77cf14c.png)

---
### 기업 시나리오
![image](https://user-images.githubusercontent.com/68271830/233270542-591dd0de-045e-47f2-ab81-851a17969e93.png)

![image](https://user-images.githubusercontent.com/68271830/233270575-f020df46-50a8-49d9-ace6-9b0e91d17730.png)

![image](https://user-images.githubusercontent.com/68271830/233270603-34ef6a8e-eac4-49f9-8432-8da10d361b01.png)

![image](https://user-images.githubusercontent.com/68271830/233270631-d206a11e-7e0e-4bb1-9c3c-145c0c8ade56.png)

![image](https://user-images.githubusercontent.com/68271830/233270660-18e96d4d-346d-495f-8f75-520c825a6e84.png)
