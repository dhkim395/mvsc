package com.koreait.mvc2.service;

import com.koreait.mvc2.DAO.MemberDAO;
import com.koreait.mvc2.DAO.PostDAO;
import com.koreait.mvc2.DTO.CommentDTO;
import com.koreait.mvc2.DTO.MemberDTO;
import com.koreait.mvc2.DTO.PostDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PostServiceImpl implements PostService {

    private PostDAO dao = new PostDAO();

    @Override
    public void isLogged(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberDTO sessionDto = (MemberDTO) req.getSession().getAttribute("user");
        if (sessionDto != null) {
            req.getRequestDispatcher("WEB-INF/views/writePost.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("login.member");
        }
    }

    @Override
    public void write(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostDTO dto = new PostDTO();
        MemberDTO sessionDto = (MemberDTO) req.getSession().getAttribute("user");
        dto.setUser_id(sessionDto.getUserid());

        dto.setTitle(req.getParameter("title"));
        dto.setContent(req.getParameter("content"));
        boolean success = dao.insertPost(dto);
        if (success) {
            resp.sendRedirect("board.post");
        } else {
            req.setAttribute("error", "게시글 작성 중 오류가 발생했습니다.");
            req.getRequestDispatcher("WEB-INF/views/writePost.jsp").forward(req, resp);
        }
    }

    @Override
    public void showPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PostDTO> postList = dao.getAllPost();
        req.setAttribute("posts", postList);
        req.getRequestDispatcher("WEB-INF/views/board.jsp").forward(req, resp);

    }

    @Override
    public void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idx = Integer.parseInt(req.getParameter("idx"));
        PostDTO onePost = dao.getOnePostByIdx(idx);
        List<CommentDTO> comments = dao.getAllCommentByBoard_idx(idx);
        req.setAttribute("onePost", onePost);
        req.setAttribute("comments", comments);
        req.getRequestDispatcher("WEB-INF/views/detail.jsp").forward(req, resp);
    }

    @Override
    public void editPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idx = Integer.parseInt(req.getParameter("idx"));
        PostDTO onePost = dao.findPostByIdx(idx);
        req.setAttribute("onePost", onePost);
        req.getRequestDispatcher("WEB-INF/views/editPost.jsp").forward(req, resp);
    }

    @Override
    public void editDone(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostDTO dto = new PostDTO();
        dto.setIdx(Integer.parseInt(req.getParameter("hidden_idx")));
        dto.setTitle(req.getParameter("title"));
        dto.setContent(req.getParameter("content"));
        boolean isUpdate = dao.updatePost(dto);
        if (isUpdate) {
            PostDTO onePost = dao.findPostByIdx(dto.getIdx());
            req.setAttribute("onePost", onePost);
            req.getRequestDispatcher("WEB-INF/views/detail.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "게시글 수정 중 오류가 발생했습니다.");
            req.getRequestDispatcher("WEB-INF/views/board.jsp").forward(req, resp);
        }
    }

    @Override
    public void deletePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idx = Integer.parseInt(req.getParameter("idx"));
        boolean isDelete = dao.deletePost(idx);
        if (isDelete) {
            resp.sendRedirect("board.post");
        }
    }

    public void registComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentDTO dto = new CommentDTO();
        MemberDTO sessionDto = (MemberDTO) req.getSession().getAttribute("user");
        dto.setUser_id(sessionDto.getUserid());
        dto.setBoard_idx(Integer.parseInt(req.getParameter("board_idx")));
        dto.setContent(req.getParameter("comment"));
        boolean success = dao.registComment(dto);
        if (success) {
            resp.sendRedirect("view.post?idx=" + dto.getBoard_idx()); // ← 새로고침해도 중복 요청 없음
        } else {
            req.setAttribute("error", "게시글 수정 중 오류가 발생했습니다.");
            req.getRequestDispatcher("WEB-INF/views/board.jsp").forward(req, resp);
        }
    }


} // class
