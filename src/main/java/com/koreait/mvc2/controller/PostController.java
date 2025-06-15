package com.koreait.mvc2.controller;

import com.koreait.mvc2.service.MemberService;
import com.koreait.mvc2.service.MemberServiceImpl;
import com.koreait.mvc2.service.PostService;
import com.koreait.mvc2.service.PostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.post")
public class PostController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PostService service = new PostServiceImpl();

    public PostController() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    protected void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String uri = req.getRequestURI();
        String context = req.getContextPath();
        String command = uri.substring(context.length());

        switch (command) {
            case "/board.post":
                service.showPost(req, resp);
                break;

            case "/write.post":
                if (req.getMethod().equals("GET")) {
                    service.isLogged(req, resp);
                } else if (req.getMethod().equalsIgnoreCase("POST")) {
                    service.write(req, resp);
                }
                break;

            case "/view.post":
                service.view(req, resp);
                break;

            case "/edit.post":
                if (req.getMethod().equals("GET")) {
                    service.editPost(req, resp);
                } else if (req.getMethod().equalsIgnoreCase("POST")) {
                    service.editDone(req, resp);
                }
                break;

            case "/delete.post":
                service.deletePost(req, resp);
                break;

            case "/comment.post":
                service.registComment(req,resp);
                break;

            default:
                // 잘못된 페이지 에러를 보내는 구문
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }


    }
} //class

