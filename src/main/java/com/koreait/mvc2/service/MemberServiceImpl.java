package com.koreait.mvc2.service;

import com.koreait.mvc2.DAO.MemberDAO;
import com.koreait.mvc2.DTO.MemberDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberServiceImpl implements MemberService {

    private MemberDAO dao = new MemberDAO();

    @Override
    public void join(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberDTO dto = new MemberDTO();
        dto.setUserid(req.getParameter("userid"));
        dto.setUserpw(req.getParameter("userpw"));
        dto.setName(req.getParameter("name"));
        dto.setHp(req.getParameter("hp"));
        dto.setEmail(req.getParameter("email"));
        dto.setGender(req.getParameter("gender"));
        dto.setSsn1(req.getParameter("ssn1"));
        dto.setSsn2(req.getParameter("ssn2"));
        dto.setZipcode(req.getParameter("zipcode"));
        dto.setAddress1(req.getParameter("address1"));
        dto.setAddress2(req.getParameter("address2"));
        dto.setAddress3(req.getParameter("address3"));
        boolean success = dao.insertMember(dto);
        req.setAttribute("success", success);
        req.getRequestDispatcher("/WEB-INF/views/result.jsp").forward(req, resp);
    }

    @Override
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userid = req.getParameter("userid");
        String userpw = req.getParameter("userpw");

        MemberDTO dto = dao.login(userid,userpw);
        if (dto != null){
            req.getSession().setAttribute("user", dto);
            req.setAttribute("loginUser",dto);
        }
        //jsp로 보내기
        req.getRequestDispatcher("/WEB-INF/views/result.jsp").forward(req, resp);
    }

    @Override
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("user");  //세션 날린다
        resp.sendRedirect("login.member");
    }

    @Override
    public void modify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberDTO sessiondto = (MemberDTO) req.getSession().getAttribute("user");
        MemberDTO dto = new MemberDTO();
        dto.setUserid(sessiondto.getUserid());
        dto.setName(req.getParameter("name"));
        dto.setHp(req.getParameter("hp"));
        dto.setEmail(req.getParameter("email"));
        dto.setGender(req.getParameter("gender"));
        dto.setZipcode(req.getParameter("zipcode"));
        dto.setAddress1(req.getParameter("address1"));
        dto.setAddress2(req.getParameter("address2"));
        dto.setAddress3(req.getParameter("address3"));

        boolean isModify=dao.updateMember(dto);
        if(isModify){
            req.getSession().setAttribute("user", dto);
        }
        req.setAttribute("isModify", isModify);
        req.getRequestDispatcher("/WEB-INF/views/mypage.jsp").forward(req, resp);
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberDTO sessiondto = (MemberDTO) req.getSession().getAttribute("user");
        if(sessiondto.getUserid()!=null){
            dao.deleteMemver(sessoindto.getUserid());
            req.getSession().invalidate();  //invalidate:삭제
        }
        resp.sendRedirect("login.member");
    }
}
