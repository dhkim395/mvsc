package com.koreait.mvc2.DAO;

import com.koreait.mvc2.DTO.CommentDTO;
import com.koreait.mvc2.DTO.MemberDTO;
import com.koreait.mvc2.DTO.PostDTO;
import com.koreait.mvc2.utill.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    public boolean insertPost(PostDTO dto) {
        String sql = "insert into board (title, content,user_id) values(?,?,?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dto.getTitle());
            pstmt.setString(2, dto.getContent());
            pstmt.setString(3, dto.getUser_id());
            return pstmt.executeUpdate() == 1; // 정상 완료 결과가 1임
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<PostDTO> getAllPost() {
        List<PostDTO> postList = new ArrayList<>();
        String sql = "select * from board order by regdate desc";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                PostDTO dto = new PostDTO();
                dto.setIdx(rs.getInt("idx"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setUser_id(rs.getString("user_id"));
                dto.setView_count(rs.getInt("view_count")); // 필요시 게시글 ID 등 추가
                dto.setRegdate(rs.getTimestamp("regdate"));
                postList.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return postList;
    }

    public PostDTO getOnePostByIdx(int idx) {
        // 1단계: 조회수 증가
        String updateSql = "UPDATE board SET view_count = view_count + 1 WHERE idx = ?";

        // 2단계: 게시글 조회
        String selectSql = "SELECT * FROM board WHERE idx = ?";

        try (Connection conn = DBUtil.getConnection()) {
            // step1: update view_count
            try (PreparedStatement updatePstmt = conn.prepareStatement(updateSql)) {

                updatePstmt.setInt(1, idx);
                updatePstmt.executeUpdate();
            }

            // step2: select post
            try (PreparedStatement selectPstmt = conn.prepareStatement(selectSql)) {
                selectPstmt.setInt(1, idx);
                ResultSet rs = selectPstmt.executeQuery();
                if (rs.next()) {
                    PostDTO dto = new PostDTO();
                    dto.setIdx(rs.getInt("idx"));
                    dto.setTitle(rs.getString("title"));
                    dto.setContent(rs.getString("content"));
                    dto.setUser_id(rs.getString("user_id"));
                    dto.setView_count(rs.getInt("view_count")); // DB에서 +1 된 값 그대로 가져옴
                    dto.setRegdate(rs.getTimestamp("regdate"));
                    return dto;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public PostDTO findPostByIdx(int idx) {
        String sql = "SELECT * FROM board WHERE idx = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, idx);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                PostDTO dto = new PostDTO();
                dto.setIdx(rs.getInt("idx"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setUser_id(rs.getString("user_id"));
                dto.setView_count(rs.getInt("view_count"));
                dto.setRegdate(rs.getTimestamp("regdate"));
                return dto;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePost(PostDTO dto) {
        String sql = "UPDATE board SET title = ?, content = ? where idx = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, dto.getTitle());
            pstmt.setString(2, dto.getContent());
            pstmt.setInt(3, dto.getIdx());
            return pstmt.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePost(int idx) {
        String sql = "DELETE FROM board WHERE idx = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, idx);
            return pstmt.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean registComment(CommentDTO dto) {
        String sql = "insert into comment (board_idx, content, user_id ) values(?,?,?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, dto.getBoard_idx());
            pstmt.setString(2, dto.getContent());
            pstmt.setString(3, dto.getUser_id());
            return pstmt.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<CommentDTO> getAllCommentByBoard_idx(int board_idx) {
        List<CommentDTO> commentList = new ArrayList<>();
        String sql = "select * from comment where board_idx = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, board_idx); // ✅ 꼭 필요!
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                CommentDTO dto = new CommentDTO();
                dto.setIdx(rs.getInt("idx"));
                dto.setBoard_idx(rs.getInt("board_idx"));
                dto.setContent(rs.getString("content"));
                dto.setUser_id(rs.getString("user_id"));
                dto.setRegdate(rs.getTimestamp("regdate"));
                commentList.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return commentList;
    }

} // class
