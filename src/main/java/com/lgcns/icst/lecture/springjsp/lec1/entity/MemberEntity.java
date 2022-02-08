package com.lgcns.icst.lecture.springjsp.lec1.entity;

public class MemberEntity {

    private String memberId;
    private String memberPw;
    private String memberName;
    private Integer point;

    public MemberEntity(String memberId, String memberPw, String memberName, Integer point) {
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.point = point;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberPw() {
        return memberPw;
    }

    public String getMemberName() {
        return memberName;
    }

    public Integer getPoint() {
        return point;
    }
}
