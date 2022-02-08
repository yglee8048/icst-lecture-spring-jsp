package com.lgcns.icst.lecture.springjsp.lec1.dto;

import com.lgcns.icst.lecture.springjsp.lec1.entity.FreeBoardEntity;

import java.sql.Date;

public class FreeBoardDTO {

    private Long id;
    private String content;
    private String writerId;
    private String writerName;
    private Date writeDate;

    public FreeBoardDTO() {
    }

    public FreeBoardDTO(FreeBoardEntity freeBoardEntity, String writerName) {
        this.id = freeBoardEntity.getId();
        this.content = freeBoardEntity.getContent();
        this.writerId = freeBoardEntity.getWriterId();
        this.writerName = writerName;
        this.writeDate = freeBoardEntity.getWriteDate();
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

    public String getWriterName() {
        return writerName;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    @Override
    public String toString() {
        return "FreeBoardDTO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", writerId='" + writerId + '\'' +
                ", writerName='" + writerName + '\'' +
                ", writeDate=" + writeDate +
                '}';
    }
}
