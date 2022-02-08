package com.lgcns.icst.lecture.springjsp.lec2.mvcController.member;

public class FreeBoardUpdateRequest {

    private Long id;
    private String content;

    public FreeBoardUpdateRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
