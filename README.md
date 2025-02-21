

# 1. 유기동물입양플랫폼, 환승주인

<div>

![01](readme/img/logo.png)

</div>

<br/>

# 2. 환승주인을 만드는 사람

| <img src="readme/img/웅휘.jpeg" width=150px><br />[진웅휘](https://github.com/woongwhee) | <img width="150" alt="내이모지" src="https://user-images.githubusercontent.com/115914261/222369114-cf875dd1-8c0f-4054-82c5-5d179abc1ef6.png"><br />[이상엽](https://github.com/Lee-sang-yeop) | <img src="readme/img/서학.jpeg" width=150px><br />[염서학](https://github.com/YEOMCODING) | <img src="readme/img/현정.jpeg" width=150px><br />[장현정](https://github.com/HyunjeongJang) | <img src="readme/img/지호.jpeg" width=150px><br />[김지호](https://github.com/KJeeu) | <img src="readme/img/도윤.jpeg" width=150px><br />[김도윤](https://github.com/kimdory) |
|:-----------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------:|

<br/>


# 3. 환승주인를 만드는 기술

- **서버**: JAVA 11, tomcat9, Servlet3, JDBC 
- **프론트엔드**:  JAVASCRIPT,JQUERY
- **데이터베이스**: ORACLE 11g
- **버전 관리**:MAVEN, Git with GitHub

<br>
<details>
<summary>디랙토리 구조</summary>
<div markdown="1">

  ```
  .
  ├──java
  │   ├──domain 
  │   │   ├──dao # 
  │   │   ├──dto # 외부api간 데이터 교환용폴더
  │   │   ├──vo # 
  │   │   └──type # enum
  │   └──web #비지니스 로직
  ├──resource
  │   ├──key # api키 관리용 xml폴더
  │   └──sql # sql문 저장용 xml폴더
  ├──webapp
  │   ├──js 자바스크립트 파일저장용폴더
  │   ├──img 이미지 리소스 저장용폴더
  │   ├──smarteditor2 스마트에디터 관련코드
  │   ├──css css파일 저장용폴더
  │   └──view# jsp파일 저장용폴더
      
file #첨부파일 저장용폴더 ($tomcathome/webapps/file)
       
  ```
</div>
</details>
<br/>

# 4. 주요 기능


## 4.1. 유기동물 조회

|                              메인페이지                               |                              유기동물  목록                               |                                상세조회                                 |
|:----------------------------------------------------------------:|:-------------------------------------------------------------------:|:-------------------------------------------------------------------:|
|                              메인페이지                               |                          유기동물 지역별 조회->상세조회                          |                           유기동물 상세조회,사진댓글                            |
| <img src="readme/gif/main.gif" alt="회원관리" style="width:100%;" /> | <img src="readme/gif/noticeSearch.gif" alt="" style="width:90%;" /> | <img src="readme/gif/noticeDetail.gif" alt="" style="width:90%;" /> |

<details>
<summary>자세히</summary>
<div markdown="1">

##### 메인페이지 
     메인페이지에서는 사용자에게 대부분의 기능에 간단하게 접근 할 수 있도록 설계 해놨습니다.
     유기동물 목록은 slick 라이브러리를 가저와서 구현했습니다.
##### 유기동물 검색 
     유기동물 검색은 비동기 형식으로 검색 할 수 있으며 페이징 형식을 
    무한 스크롤 형식으로 만들어놔 부드럽게 넘어갈 수 있습니다.
##### 유기동물 상세조회  
    유기동물 상세 조회로 해당 동물에 대한 자세한 정보를 조회 할 수 있으며
    유기동물에게 따뜻한 응원의 메세지와 보호소에 방문해서 찍은 사진을 댓글을 통해 남길 수 있다. 


</div>
</details>

<br/>
## 4.2. 입양 및 봉사 신청

|                             유기동물 입양신청                             |                               보호소 봉사신청                                |                                 신청 수락                                 |
|:-----------------------------------------------------------------:|:---------------------------------------------------------------------:|:---------------------------------------------------------------------:|
|                         유기동물 공고를 통한 입양신청                          |                             등록된 보호소별 봉사신청                             |                        (등록된 보호소관계자)신청자에게 문자보내기                        |
| <img src="readme/gif/adopt.gif" alt="회원관리" style="width:100%;" /> | <img src="readme/gif/volunteerApply.gif" alt="" style="width:90%;" /> | <img src="readme/gif/volunteerAgree.gif" alt="" style="width:90%;" /> |

<details>
<summary>자세히</summary>
<div markdown="1">

##### 유기동물 입양신청
     등록된 유기동물에 대한 입양신청서를 작성 할 수 있다. 
    진정성 있는 장문의 입양신청서를 사용자에게 작성하게 강제한다.
##### 보호소 봉사신청
    사이트에 등록된 보호소에 봉사를 신청을 할 수 있는 페이지다.
##### 신청 수락
    사이트에 등록된 보호소 관계자의 경우 사이트에서 들어온 입양신청서와 봉사신청서를 간편하게 조회 할 수 있다. 
    확인 여부를 체크하여 확인되지 않은 신청서의 경우 사이트 관리자가 한번더 공지를 해줄수 있도록 했다.
    조회한 정보를 바탕으로 수락여부를 문자로 발송 할 수 있는 서비스도 제공한다.
    

</div>
</details>

<br/>
## 4.2. 후기게시판

|                                후기게시판 목록                                |                               후기 작성                                |                               후기 상세보기                               |
|:----------------------------------------------------------------------:|:------------------------------------------------------------------:|:-------------------------------------------------------------------:|
| <img src="readme/gif/reviewList.gif" alt="회원관리" style="width:100%;" /> | <img src="readme/gif/naverEditer.gif" alt="" style="width:90%;" /> | <img src="readme/gif/reviewDetail.gif" alt="" style="width:90%;" /> |

<details>
<summary>자세히</summary>
<div markdown="1">

##### 후기게시판 목록
     등록된 후기들을 비동기 처리된 무한스크롤로 10개 단위로 볼 수 있다.
##### 보호소 봉사신청
     게시판작성을 네이버 에디터를 통해 구현해 다체로운 게시글을 작성 할 수 있다.
     최대 5개의 첨부파일을 첨부 할 수 도록 설정했다.
##### 후기 상세보기
     후기 게시글을 상세 조회하는 페이지
     로그인된 사용자의 경우 댓글을 작성할수 있고 작성된 게시글과 댓글을 신고 할 수 있다.
     작성자의 경우 게시글을 수정 삭제 할 수 있다.
</div>
</details>

<br/>

# 5. 문서

### 기획보고서
  https://drive.google.com/file/d/1L6zZkbqxW-iPZNCHpkdetDZk2rc9NBJs/view?usp=share_link
### ERD다이어그램
  https://www.erdcloud.com/d/KswtGB5amNor7KgTn
### 데이터베이스기술서
  https://docs.google.com/spreadsheets/d/1J3zE1rpJmOWnnTh2TjGaZumdDqmRdL-S/edit?usp=share_link&ouid=114521314021540923644&rtpof=true&sd=true
### UI설계
  https://drive.google.com/file/d/18kj2KG9glaGqy_R7XCG7SbCxSkAlR_o4/view?usp=share_link
### 최종보고서
  https://docs.google.com/presentation/d/10xP-GvdzqPMvrpfWyeb0FNHUrRTNBbTGDzxrwljUmnE/edit?usp=sharing
<br/>
