package com.hailey.web.entity;

import java.sql.Date;

public class NoticeView extends Notice{

    private int cmtCount;

    public int getCmtCount() {
        return cmtCount;
    }

    public void setCmtCount(int cmtCount) {
        this.cmtCount = cmtCount;
    }


    // 기본 생성자
    public NoticeView(){

    }

    // 생성자
    public NoticeView(int boardno, String subject, Date writeday, String userid, int viewcount, String files, boolean pub, int cmtCount) {

        // NoticeView는 Notice entity를 슈퍼클래스로 가진다.
        super(boardno, subject, writeday, userid, viewcount, files, "", pub);
        this.cmtCount = cmtCount;
    }
}
