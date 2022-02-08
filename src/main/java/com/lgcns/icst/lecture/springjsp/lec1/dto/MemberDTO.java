package com.lgcns.icst.lecture.springjsp.lec1.dto;

import com.lgcns.icst.lecture.springjsp.lec1.entity.MemberEntity;

public class MemberDTO {

    private String memberId;
    private String memberPw;
    private String memberName;
    private Integer point;

    public MemberDTO(MemberEntity memberEntity) {
        this.memberId = memberEntity.getMemberId();
        this.memberPw = memberEntity.getMemberPw();
        this.memberName = memberEntity.getMemberName();
        this.point = memberEntity.getPoint();
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
