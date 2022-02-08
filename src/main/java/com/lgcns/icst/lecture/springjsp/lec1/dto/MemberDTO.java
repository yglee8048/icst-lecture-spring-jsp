package com.lgcns.icst.lecture.springjsp.lec1.dto;

import com.lgcns.icst.lecture.springjsp.lec1.entity.MemberEntity;
import com.lgcns.icst.lecture.springjsp.lec1.entity.MemberRank;

public class MemberDTO {

    private String memberId;
    private String memberPw;
    private String memberName;
    private Integer point;
    private MemberRank rank;

    public MemberDTO() {
    }

    public MemberDTO(MemberEntity memberEntity) {
        this.memberId = memberEntity.getMemberId();
        this.memberPw = memberEntity.getMemberPw();
        this.memberName = memberEntity.getMemberName();
        this.point = memberEntity.getPoint();
    }

    public MemberEntity toEntity() {
        return new MemberEntity(memberId, memberPw, memberName, point);
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

    public String getRank() {
        return rank.name();
    }

    public void setRank(MemberRank rank) {
        this.rank = rank;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setMemberPw(String memberPw) {
        this.memberPw = memberPw;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
