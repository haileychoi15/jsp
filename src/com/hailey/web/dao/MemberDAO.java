package com.hailey.web.dao;

import java.sql.*;

public class MemberDAO {

    private Connection conn;
    private PreparedStatement pstmt;

    public MemberDAO(){

        try {
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String user = "myorauser";
            String password = "eclass";

            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }


    // 로그인 매소드
    public int login(String userid, String passwd) {

        ResultSet rs;

        String sql = "select passwd from jdbc_member\n" +
                "where userid = ? and status = 1";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            rs = pstmt.executeQuery();

            if(rs.next()){

                System.out.println(rs.getString(1));
                System.out.println(passwd);

                if(rs.getString(1).equals(passwd))
                    return 1; //로그인 성공
                else
                    return 0; // 비밀번호 불일치
            }
            return -1; //아이디가 없음

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -2; //데이터 베이스 오류
    }

    // 회원가입 매소드
}
