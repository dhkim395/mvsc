package com.koreait.mvc2.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommentFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        if (loggedIn) {
            chain.doFilter(request, response); // 로그인되어있으면 다음 필터나 서블릿으로 진행
        } else {
            resp.sendRedirect(req.getContextPath() + "/view.member");  // 로그인 안되어있으면 login 화면으로 강제이동
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
