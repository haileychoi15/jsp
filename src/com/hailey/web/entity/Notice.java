package com.hailey.web.entity;

import java.sql.Date;

public class Notice {

    private int boardno;
    private String subject;
    private Date writeday;
    private String userid;
    private int viewcount;
    private String files;
    private String contents;
    private boolean pub;

    // 기본 생성자
    public Notice() {
    }

    // overload 생성자
    public Notice(int boardno, String subject, Date writeday, String userid, int viewcount, String files, String contents, boolean pub) {

        this.boardno = boardno;
        this.subject = subject;
        this.writeday = writeday;
        this.userid = userid;
        this.viewcount = viewcount;
        this.files = files;
        this.contents = contents;
        this.pub = pub;

    }



    public int getBoardno() {
        return boardno;
    }

    public String getSubject() {
        return subject;
    }

    public Date getWriteday() {
        return writeday;
    }

    public String getUserid() {
        return userid;
    }

    public int getViewcount() {
        return viewcount;
    }

    public String getFiles() { return files; }

    public String getContents() {
        return contents;
    }

    public boolean getPub() {
        return pub;
    }

    public void setBoardno(int boardno) {
        this.boardno = boardno;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setWriteday(Date writeday) {
        this.writeday = writeday;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setViewcount(int viewcount) {
        this.viewcount = viewcount;
    }

    public void setPub(boolean pub) {
        this.pub = pub;
    }


    public void setFiles(String files) { this.files = files; }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "boardno=" + boardno +
                ", subject='" + subject + '\'' +
                ", writeday=" + writeday +
                ", userid='" + userid + '\'' +
                ", viewcount=" + viewcount +
                ", contents='" + contents + '\'' +
                ", files=" + files +
                ", pub=" + pub +
                '}';
    }
}
