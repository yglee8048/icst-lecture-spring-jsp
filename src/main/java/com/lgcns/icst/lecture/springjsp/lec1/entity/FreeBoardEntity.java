package com.lgcns.icst.lecture.springjsp.lec1.entity;

import java.sql.Date;

public class FreeBoardEntity {

    private Long id;
    private String content;
    private String writerId;
    private Date writeDate;

    public FreeBoardEntity(Long id, String content, String writerId, Date writeDate) {
        this.id = id;
        this.content = content;
        this.writerId = writerId;
        this.writeDate = writeDate;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getWriterId() {
        return writerId;
    }

    public Date getWriteDate() {
        return writeDate;
    }
}
