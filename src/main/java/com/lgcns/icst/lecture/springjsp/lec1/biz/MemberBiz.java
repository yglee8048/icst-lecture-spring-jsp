package com.lgcns.icst.lecture.springjsp.lec1.biz;

import com.lgcns.icst.lecture.springjsp.lec1.dao.MemberDAO;
import com.lgcns.icst.lecture.springjsp.lec1.dto.MemberDTO;
import com.lgcns.icst.lecture.springjsp.lec1.entity.MemberEntity;
import com.lgcns.icst.lecture.springjsp.lec1.util.JdbcUtil;

import java.sql.Connection;

public class MemberBiz {

    public MemberDTO login(String memberId, String memberPw) throws Exception {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();

            MemberDAO memberDAO = new MemberDAO();
            MemberEntity member = memberDAO.findMemberById(connection, memberId);

            if (member == null) {
                throw new Exception("존재하지 않는 아이디입니다.");
            } else if (!member.getMemberPw().equals(memberPw)) {
                throw new Exception("비밀번호가 일치하지 않습니다.");
            } else {
                return new MemberDTO(member);
            }
        } finally {
            JdbcUtil.close(connection);
        }
    }

    public void signUp(String memberId, String memberPw, String memberName) throws Exception {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();

            MemberDAO memberDAO = new MemberDAO();
            boolean result = memberDAO.saveMember(connection, memberId, memberPw, memberName);
            if (result) {
                JdbcUtil.commit(connection);
            } else {
                throw new Exception("회원 가입에 실패했습니다.");
            }
        } catch (Exception e) {
            JdbcUtil.rollback(connection);
            throw e;
        } finally {
            JdbcUtil.close(connection);
        }
    }

    public MemberDTO findById(String memberId) throws Exception {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();

            MemberDAO memberDAO = new MemberDAO();
            MemberEntity member = memberDAO.findMemberById(connection, memberId);

            if (member == null) {
                throw new Exception("존재하지 않는 아이디입니다.");
            }
            return new MemberDTO(member);
        } finally {
            JdbcUtil.close(connection);
        }
    }
}
