package com.koreait.mvc2.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MemberService {
    void join(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void modify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
//뷰에서 만들고 커트롤러에 만들고 인터페이스서비스 만들고 impl 가서 만들고 dao 만들고 다시 impl에서 result로 보내면 result로 갔다가 다시 controller로 가서 마무리
