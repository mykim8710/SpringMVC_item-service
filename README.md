# SpringMVC EX Project - Item Service

## Project Spec
- 프로젝트 선택
    - Project: Gradle Project
    - Spring Boot: 2.7.3
    - Language: Java
    - Packaging: Jar
    - Java: 11
- Project Metadata
    - Group: com.example
    - Artifact: item-service
    - Name: springmvc-item-service
    - Package name: com.example.itemservice
- Dependencies: **Spring Web**, **Thymeleaf**, **Lombok**

## Package Design
```
└── src
    ├── main
    │   ├── java
    │   │   └── com.example.itemservice
    │   │               ├── domain
    │   │               │      └── item
    │   │               │            ├── Item(C)            |  상품 도메인 객체
    │   │               │            └── ItemRepository(C)  |  상품 저장소 Repository
    │   │               ├──  web
    │   │               │      └── basic
    │   │               │            └── BasicItemController(C)  |  컨트롤러
    │   │               ├── ViewController(C)
    │   │               └── SpringmvcItemServiceApplication(C)
    │   └── resource
    │       ├── static
    │       │     └──  css 
    │       │           └── bootstrap.min.css  |  bootstrap
    │       ├── template
    │       │     ├── index.html  |   Welcome page
    │       │     └── basic
    │       │            ├── item.html        |  상품조회 page
    │       │            ├── items.html       |  상품목록 page
    │       │            ├── saveForm.html    |  상품등록 page
    │       │            └── updateForm.html  |  상품수정 page
    │       └── application.yaml
    ├── test
    │   ├── java
    │   │   └── com.example.itemservice
    │   │               └── domain
    │   │                      └── item
    │   │                            └── ItemRepositoryTest(C)  |  상품 저장소 Repository TestCode

```

## 요구사항 정의
- 주제 : 상품을 관리할 수 있는 서비스
- **상품 도메인 모델**
  - 상품 ID, item id
  - 상품명, item name
  - 가격, item price
  - 수량, item quantity
- **상품 관리 기능**
  - 상품 목록 조회
  - 상품 상세 조회
  - 상품 등록
  - 상품 수정
  - 상품 삭제

## 요청 API 설계
- 상품 **관리 HTTP API URL, Not Rest API**
  - 상품 목록 페이지 :  GET     /basic/items
  - 상품 조회 페이지 :  GET     /basic/items/{itemId}
  - 상품 등록 페이지 :  GET     /basic/items/save
  - 상품 등록            : POST    /basic/items/save
  - 상품 수정 페이지 :   GET    /basic/items/{itemId}/update
  - 상품 수정            : POST    /basic/items/{itemId}/update
  - 상품 삭제            : POST    /basic/items/delete