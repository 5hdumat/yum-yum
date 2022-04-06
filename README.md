# 토이 프로젝트 "yum-yum"
실제 배포/운영까지 **full-cycle develop**을 목표로 합니다.

## 개요

내가 키우는 반려동물이 나와 똑같은 사람이 된다면?

"8살이었던 나의 강아지가 56살이 되어있고, 매일 먹는 사료는 치즈버거 정도의 성분이래요.  
정말 귀엽지만 치즈버거라니.. 조금 더 건강한 사료로 바꿔줘야 할 것 같아요."  

<img src="https://i.imgur.com/Wi2wRbj.png" width="60%">
                                                                                                                  
이 처럼 재미있지만 나의 반려동물을 위한 중요한 정보를 피드백 해줄 수 있는 프로젝트를 구상해보았습니다.  
 
## Entity Model

간단한 Domain & Entity Model Diagram 입니다. 매핑된 연관관계를 `1`과 `*`로 표현했습니다.

<img src="https://i.imgur.com/GMfphK6.png" width="80%">
<img src="https://i.imgur.com/gI2ibJT.png" width="80%">

## 기능

- 회원
  - 회원 등록
  - 회원의 반려동물 등록
  - 회원의 반려동물 수정
  - 회원의 반려동물 삭제
  - 회원의 반려동물 목록
  - 나의 반려동물 사료 분석하기

- 관리자
  - 사료 
    - 사료 등록
    - 사료 수정
    - 사료 삭제
    - 사료 목록
    - 사료 별 리콜 이력
      - 리콜 등록
      - 리콜 수정
      - 리콜 삭제
      - 리콜 목록 
  - 품종
    - 품종 등록
    - 품종 수정
    - 품종 삭제
    - 품종 목록

## 기술 스택
- **Language**
  - java
   
- **Framework**
  - Spring, Spring Boot 

- **ORM**
  - JPA(Hibernate5)

- **Library**
  - Querydsl 

 
