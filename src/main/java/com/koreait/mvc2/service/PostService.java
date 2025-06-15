package com.koreait.mvc2.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface PostService {
    void isLogged(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void write(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void showPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void editPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void editDone(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void deletePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void registComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

}
