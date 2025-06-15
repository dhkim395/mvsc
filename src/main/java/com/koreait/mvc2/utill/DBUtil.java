package com.koreait.mvc2.utill;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {
    // DataSource: JNDI 설정을 위해 필요한 객체.
    private static DataSource ds;
    // JNDI 연결
    static {
        try {
            // ctx: JNDX를 바라봄
            Context ctx = new InitialContext();
            Context env = (Context) ctx.lookup("java:comp/env");
            ds = (DataSource) env.lookup("jdbc/mydb"); // context.xml의 name을 입력해줌
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
} // class


