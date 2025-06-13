package com.koreait.mvc1.data;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
    public boolean insertMember(MemberDTO member) {
        String sql = """
    insert into member(userid,userpw,name,email,hp,email,gender,ssn1,ssn2
    ,zipcode,address1,address2,address3) values(?,?,?,?,?,?,?,?,?,?,?,?)""";
        try(Connection conn = DBUtill.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, member.getUserid());
            pstmt.setString(2, member.getUserpw());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getEmail());
            pstmt.setString(5, member.getHp());
            pstmt.setString(6, member.getEmail());
            pstmt.setString(7, member.getGender());
            pstmt.setString(8, member.getSsn1());
            pstmt.setString(9, member.getSsn2());
            pstmt.setString(10, member.getZipcode());
            pstmt.setString(11, member.getAddress1());
            pstmt.setString(12, member.getAddress2());
            return pstmt.executeUpdate() == 1 ;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public MemberDTO login(String userid, String userpw) {
        String sql = "select * from member where userid = ? and userpw = ?";

        try(Connection conn = DBUtill.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, userid);
            pstmt.setString(2, userpw);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                MemberDTO member = new MemberDTO();
                member.setIdx(rs.getInt("idx"));
                member.setUserid(rs.getString("userid"));
                member.setName(rs.getString("name"));
                member.setHp(rs.getString("hp"));
                member.setEmail(rs.getString("email"));
                member.setPoint(Integer.parseInt(rs.getString("point")));
                return member;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean updateMaber(MemberDTO dto){
        String sql = """
                update member set name=?, hp=?, email=?, gender=?,
                zipcode=?, address1=?, address2=?, address3=? where userid=?
                """;
                try(Connection conn= DBUtill.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
                    pstmt.setString(1,dto.getName());
                    pstmt.setString(2,dto.getHp());
                    pstmt.setString(3,dto.getEmail());
                    pstmt.setString(4,dto.getGender());
                    pstmt.setString(5,dto.getZipcode());
                    pstmt.setString(6,dto.getAddress1());
                    pstmt.setString(7,dto.getAddress2());
                    pstmt.setString(8,dto.getAddress3());
                    pstmt.setString(9,dto.getUserid());
                    return pstmt.executeUpdate() == 1 ;
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
    }
    }

