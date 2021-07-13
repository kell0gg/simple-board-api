# Simple board - Springboot

## APIs

| 기능             | METHOD | Redirect URL        |
| ---------------- | ------ | ------------------- |
| 게시글 목록 조회 | GET    | /posts, /posts/size |
| 게시글 등록      | POST   | /post/register      |
| 게시글 수정      | PUT    | /post/update/{id}   |
| 게시글 삭제      | DELETE | /post/delete/{id}   |
| 게시글 조회      | GET    | /post/{id}          |

## Entity

- BaseEntity
- Post Entity

## DTO

- PostDTO
- PageRequestDTO
- PageResultDTO

## Service

- PostService
- PostServiceImpl

## Repository

- PostRepository

