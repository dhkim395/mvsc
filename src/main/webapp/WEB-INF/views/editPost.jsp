<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2025-06-13
  Time: 오후 4:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<p><a href="mypage.member">마이페이지</a> | <a href="logout.member">로그아웃</a> | <a href="board.post">게시판</a></p>
<h2>게시글을 수정해보세요🤢</h2>
<form method="POST" action="edit.post">
  <p>작성자: ${user.userid}</p>
  <p>작성일: ${onePost.regdate}</p>
  <p>제목: <input type="text" name="title" value="${onePost.title}" ></p>
  <p>내용: <textarea name="content">${onePost.content}</textarea></p>
  <input type="hidden" name="hidden_idx" value="${onePost.idx}">
  <p><button type="submit">작성</button></p>
</form>
<p><a href="board.post">게시판으로 돌아가기</a></p>
</body>
</html>
