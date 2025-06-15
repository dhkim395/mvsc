<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2025-06-13
  Time: 오후 3:22
  To chaange this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
  com.koreait.mvc2.DTO.MemberDTO user = (com.koreait.mvc2.DTO.MemberDTO) session.getAttribute("user");
%>
<html>
<head>
  <title>게시물 상세페이지</title>
</head>
<body>
<p><a href="mypage.member">마이페이지</a> | <a href="logout.member">로그아웃</a> | <a href="board.post">게시판</a></p>
<h2>제목: ${onePost.title}</h2>
<p>작성자: ${onePost.user_id}</p>
<p>작성일: ${onePost.regdate}</p>
<p>조회수: ${onePost.view_count}</p>
<p>${onePost.content}</p>
<c:choose>
  <c:when test="${onePost.user_id eq user.userid}">
    <button><a href="edit.post?idx=${onePost.idx}">수정하기</a></button>
    <form method="post" action="delete.post" onsubmit="return confirm('정말 삭제하시겠습니까?')">
      <input type="hidden" name="idx" value="${onePost.idx}">
      <button type="submit">삭제하기</button>
    </form>

  </c:when>
</c:choose>
<p><a href="board.post">게시판으로 돌아가기</a></p>

<br>
<hr>
<%--댓글 쓰는 란--%>
<h3>댓글</h3>
<form method="post" action="comment.post">
  <input type="hidden" name="board_idx" value="${onePost.idx}">
  <p>댓글 쓰기: <textarea name="comment" placeholder="댓글을 작성해보세요"></textarea></p>
  <button type="submit">작성</button>
</form>
<hr>
<%--댓글 보이는 란--%>
<c:forEach var="comment" items="${comments}" varStatus="status">

  <p>작성자:${comment.user_id}</p>
  <p>작성일:${comment.regdate}</p>
  <p>내용:${comment.content}</p>
  <button type="button" onclick="showRecomment(${status.index})">대댓글 보기</button>
  <div id="reCommentBox-${status.index}" style="display: none;">
    <form method="post" action="reComment.post">
      <input type="hidden" name="comment_idx" value="${comment.idx}">
      <textarea name="recontent" placeholder="대댓글을 입력하세요"></textarea><br>
      <button type="submit">대댓글 달기</button>
    </form>
  </div>
  <hr>

</c:forEach>
<script>
  function showRecomment(index) {
    const box = document.getElementById("reCommentBox-" + index);
    if (box.style.display === "none") {
      box.style.display = "block";
    } else {
      box.style.display = "none";
    }
  }
</script>


</body>
</html>
