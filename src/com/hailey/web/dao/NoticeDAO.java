package com.hailey.web.dao;

import com.hailey.web.entity.Notice;
import com.hailey.web.entity.NoticeView;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NoticeDAO {

    // 이름이 중복되는 함수는 기능이 같다는 것을 의미하고 코드중복을 줄이기 위해 파라미터가 가장 많은 함수를
    // 파라미터가 적은 함수에서 리턴해와서 코드를 재사용한다.
    String url = "jdbc:oracle:thin:@localhost:1521/xe";






    // 관리자가 글 일괄 삭제하기
    public int removeNoticeAll(int[] boardnos){ // 몇 개가 삭제되었는지 개수를 반환
        return 0;
    }

    // 관리자가 글 일괄 공개하기 (사용자가 어떤 형식의 데이터를 보내던 유연하게 받기 위햐서 여러 타입 구현)
    public int pubNoticeAll(int[] oBoardnos, int[] cBoardnos){ // 몇 개가 공개되었는지 개수를 반환

        List<String> oBoardnosList = new ArrayList<>();
        for(int i=0; i<oBoardnos.length; i++)
            oBoardnosList.add(String.valueOf(oBoardnos[i]));

        List<String> cBoardnosList = new ArrayList<>();
        for(int i=0; i<cBoardnos.length; i++)
            cBoardnosList.add(String.valueOf(cBoardnos[i]));

        return pubNoticeAll(oBoardnosList, cBoardnosList);
    }

    public int pubNoticeAll(List<String> oBoardnos, List<String> cBoardnos){

        // join은 string type 컬렉션이나 String만 가능하다.
        String oBoardnosCSV = String.join(",", oBoardnos);
        String cBoardnosCSV = String.join(",", cBoardnos);

        return pubNoticeAll(oBoardnosCSV, cBoardnosCSV);
    }
    public int pubNoticeAll(String oBoardnosCSV, String cBoardnosCSV){ // CSV(Comma Seperated Value) : 콤마로 구분된 값

        int result = 0;

        // 선택적으로 실행되어야하는 것이라면 데이터 인자를 만들면되고 sql문을 두개 만들 필요 없다.
        String sqlOpen = "update jdbc_board set pub = 1 where boardno in ("+oBoardnosCSV+")";
        String sqlClose = String.format("update jdbc_board set pub = 0 where boardno in (%s)", cBoardnosCSV);

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, "myorauser", "eclass");

            Statement stOpen = con.createStatement();
            result += stOpen.executeUpdate(sqlOpen);

            Statement stClose = con.createStatement();
            result += stOpen.executeUpdate(sqlClose);

            stOpen.close();
            stClose.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    // 글쓰기
    public int insertNotice(Notice notice){ // Notice 객체를 받아서 서버에 넣어준다.

        int result = 0;

        String sql = "insert into jdbc_board(boardno, subject, contents, fk_userid, boardpasswd, pub, files)\n" +
                "values (BOARD_SEQ.nextval, ?, ?, ?, '1234', ?, ?)";

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, "myorauser", "eclass");
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, notice.getSubject());
            st.setString(2, notice.getContents());
            st.setString(3, notice.getUserid());
            st.setBoolean(4, notice.getPub());
            st.setString(5, notice.getFiles());

            result = st.executeUpdate();

            st.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return result;

    }
    // 글 삭제
    public int deleteNotice(int boardno){
        return 0;

    }
    // 글 수정
    public int updateNotice(Notice notice){
        return 0;

    }

    // 최근 글 불러오기
    public List<Notice> getNoticeNewestList(){

        return null;
    }

    public List<NoticeView> getNoticeList(){

        return getNoticeList("subject", "", 1); // query에 특정값이 없으면 전체검색을 의미
    }

    public List<NoticeView> getNoticeList(int page){

        return getNoticeList("subject", "", page);
    }


    public List<NoticeView> getNoticeList(String field, String query, int page){

        List<NoticeView> list = new ArrayList<>();

        String sql = "select * from (select rownum num, n.* " +
                "from (select * from notice_view where "+field+" like ? order by writeday desc) n)\n" +
                "where num between ? and ?";
        // 원래 jdbc_board 테이블인데 cmt_count를 추가한 notice_view로 가져와서 댓글 개수 알 때 코드의 재사용!!

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, "myorauser", "eclass");
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, "%"+query+"%");
            st.setInt(2, 1+(page-1)*10);
            st.setInt(3, page*10);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int boardno = rs.getInt("boardno");
                String subject = rs.getString("subject");
                Date writeday = rs.getDate("writeday");
                String userid = rs.getString("FK_userid");
                int viewcount = rs.getInt("viewcount");
                String files = rs.getString("files");
                // String contents = rs.getString("contents");
                int cmtCount = rs.getInt("cmt_count");
                boolean pub = rs.getBoolean("pub");

                NoticeView notice = new NoticeView( boardno,
                        subject,
                        writeday,
                        userid,
                        viewcount,
                        files,
                        pub,
                        cmtCount
                ); // 순서 확인하기

                list.add(notice);

            }

            rs.close();
            st.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    public List<NoticeView> getNoticePubList(String field, String query, int page) {

        List<NoticeView> list = new ArrayList<>();

        String sql = "select * from (select rownum num, n.* " +
                "from (select * from notice_view where "+field+" like ? order by writeday desc) n)\n" +
                "where pub = 1 and num between ? and ?";
        // 원래 jdbc_board 테이블인데 cmt_count를 추가한 notice_view로 가져와서 댓글 개수 알 때 코드의 재사용!!

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, "myorauser", "eclass");
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, "%"+query+"%");
            st.setInt(2, 1+(page-1)*10);
            st.setInt(3, page*10);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int boardno = rs.getInt("boardno");
                String subject = rs.getString("subject");
                Date writeday = rs.getDate("writeday");
                String userid = rs.getString("FK_userid");
                int viewcount = rs.getInt("viewcount");
                String files = rs.getString("files");
                // String contents = rs.getString("contents");
                int cmtCount = rs.getInt("cmt_count");
                boolean pub = rs.getBoolean("pub");

                NoticeView notice = new NoticeView( boardno,
                        subject,
                        writeday,
                        userid,
                        viewcount,
                        files,
                        pub,
                        cmtCount
                ); // 순서 확인하기

                list.add(notice);

            }

            rs.close();
            st.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return list;

    }



    public int getNoticeCount(){

        return getNoticeCount("subject", "");
    }

    // 글 목록 중 paging이 되지 않은 글의 개수
    public int getNoticeCount(String field, String query){

        int count = 0;

        String sql = "select count(boardno) COUNT from jdbc_board " +
                "where "+field+" like ?";

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, "myorauser", "eclass");
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, "%"+query+"%");

            ResultSet rs = st.executeQuery();

            if(rs.next())
                count = rs.getInt("count");

            rs.close();
            st.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public Notice getNotice(int boardno){

        Notice notice = null;

        String sql = "select * from jdbc_board where boardno=?";

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, "myorauser", "eclass");
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, boardno);

            ResultSet rs = st.executeQuery();

            if(rs.next()) {

                int nboardno = rs.getInt("boardno");
                String subject = rs.getString("subject");
                Date writeday = rs.getDate("writeday");
                String userid = rs.getString("FK_userid");
                int viewcount = rs.getInt("viewcount");
                String files = rs.getString("files");
                String contents = rs.getString("contents");
                boolean pub = rs.getBoolean("pub");


                notice = new Notice( nboardno,
                        subject,
                        writeday,
                        userid,
                        viewcount,
                        files,
                        contents,
                        pub
                ); // 순서 확인하기
            }

            rs.close();
            st.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return notice;
    }

    public Notice getNextNotice(int boardno){

        Notice notice = null;

        String sql = "select * from jdbc_board\n" +
                "where boardno > ? and rownum = 1"; // 현재 boardno 보다 큰 부분에서 boardno 순서 중에 첫번째꺼

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, "myorauser", "eclass");
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, boardno);

            ResultSet rs = st.executeQuery();

            if(rs.next()) {

                int nboardno = rs.getInt("boardno");
                String subject = rs.getString("subject");
                Date writeday = rs.getDate("writeday");
                String userid = rs.getString("FK_userid");
                int viewcount = rs.getInt("viewcount");
                String files = rs.getString("files");
                String contents = rs.getString("contents");
                boolean pub = rs.getBoolean("pub");

                notice = new Notice( nboardno,
                        subject,
                        writeday,
                        userid,
                        viewcount,
                        files,
                        contents,
                        pub
                ); // 순서 확인하기
            }

            rs.close();
            st.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return notice;
    }

    public Notice getPrevNotice(int boardno){

        Notice notice = null;

        String sql = "select * from (select * from jdbc_board\n" +
                "where boardno < ? order by boardno desc)\n" +
                "where rownum = 1";


        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, "myorauser", "eclass");
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, boardno);

            ResultSet rs = st.executeQuery();

            if(rs.next()) {

                int nboardno = rs.getInt("boardno");
                String subject = rs.getString("subject");
                Date writeday = rs.getDate("writeday");
                String userid = rs.getString("FK_userid");
                int viewcount = rs.getInt("viewcount");
                String files = rs.getString("files");
                String contents = rs.getString("contents");
                boolean pub = rs.getBoolean("pub");

                notice = new Notice( nboardno,
                        subject,
                        writeday,
                        userid,
                        viewcount,
                        files,
                        contents,
                        pub
                ); // 순서 확인하기
            }

            rs.close();
            st.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return notice;
    }

    // HashMap으로 댓글 개수 전달하기
    public HashMap<Integer, Integer> getCommentCountMap(){

        HashMap<Integer, Integer> map = new HashMap<>();

        String sql = "select fk_boardno, count(*) " +
                "from jdbc_comment " +
                "group by fk_boardno";

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, "myorauser", "eclass");
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while(rs.next())
                map.put(rs.getInt(1), rs.getInt(2));

            rs.close();
            st.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return map;
    }

    public int deleteNoticeAll(int[] boardnos) {

        int result = 0;
        String params = "";

        for(int i=0; i<boardnos.length; i++) {
            params += boardnos[i];

            if(i < boardnos.length-1)
                params += ",";
        }

        String sql = "delete jdbc_board where boardno in ("+params+")";

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, "myorauser", "eclass");
            Statement st = con.createStatement();

            result = st.executeUpdate(sql);

            st.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}


