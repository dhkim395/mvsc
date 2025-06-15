package com.koreait.mvc2.DTO;

import java.sql.Timestamp;

public class CommentDTO {
    private int idx;
    private int board_idx;
    private String content;
    private String user_id;
    private Timestamp regdate;

    public CommentDTO() {
    }

    public CommentDTO(int idx, int board_idx, String content, String user_id, Timestamp regdate) {
        this.idx = idx;
        this.board_idx = board_idx;
        this.content = content;
        this.user_id = user_id;
        this.regdate = regdate;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getBoard_idx() {
        return board_idx;
    }

    public void setBoard_idx(int board_idx) {
        this.board_idx = board_idx;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Timestamp getRegdate() {
        return regdate;
    }

    public void setRegdate(Timestamp regdate) {
        this.regdate = regdate;
    }
}
