package com.hailey.web.dao;

import com.hailey.web.entity.Member;

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


    // 로그인
    public Member login(String userid, String passwd) {

        ResultSet rs;
        Member loginUser = new Member();
        // loginUser = null; 이렇게 하면 안되네

        String sql = "select userid, passwd, name, mobile, point, to_char(registerday,'yyyy-MM-dd') as registerday" +
                ", gender, email, to_char(birthday,'yyyy-MM-dd') as birthday " +
                " from jdbc_member\n" +
                " where userid = ? and passwd = ? and status = 1";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            pstmt.setString(2, passwd);
            rs = pstmt.executeQuery();

            if(rs.next()){

                loginUser.setUserid(rs.getString(1));
                loginUser.setPasswd(rs.getString(2));
                loginUser.setName(rs.getString(3));
                loginUser.setMobile(rs.getString(4));
                loginUser.setPoint(rs.getInt(5));
                loginUser.setRegisterday(rs.getString(6));
                loginUser.setGender(rs.getString(7));

                // 값이 없을 수도 있는 데이터
                if(rs.getString(8) != null)
                    loginUser.setEmail(rs.getString(8));
                if(rs.getString(9) != null)
                    loginUser.setBirthday(rs.getString(9));


                return loginUser; //로그인 성공

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // 로그인 실패
    }

    // 회원가입
    public int join(Member member) {

        String sql = "insert into jdbc_member\n" +
                "values (user_seq.nextval, ?, ?, ?, ?, 0, sysdate, 1, ?, ?, ?)\n" +
                "\n";

        try {

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getUserid());
            pstmt.setString(2, member.getPasswd());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getMobile());
            pstmt.setString(5, member.getGender());
            pstmt.setString(6, member.getEmail());
            pstmt.setString(7, member.getBirthday());

            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1; //데이터베이스 오류

    }


}
