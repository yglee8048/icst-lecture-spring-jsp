package com.lgcns.icst.lecture.springjsp.lec1.biz;

import com.lgcns.icst.lecture.springjsp.lec1.dao.MemberDAO;
import com.lgcns.icst.lecture.springjsp.lec1.dto.MemberDTO;
import com.lgcns.icst.lecture.springjsp.lec1.entity.MemberEntity;
import com.lgcns.icst.lecture.springjsp.lec1.entity.MemberRank;
import com.lgcns.icst.lecture.springjsp.lec1.util.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
public class MemberBiz {

    private final MemberDAO memberDAO;
    private final RankPolicy rankPolicy;

    @Autowired
    public MemberBiz(MemberDAO memberDAO, @Qualifier("fixed-rank") RankPolicy rankPolicy) {
        this.memberDAO = memberDAO;
        this.rankPolicy = rankPolicy;
    }

    public MemberDAO getMemberDAO() {
        return memberDAO;
    }

    public MemberDTO login(String memberId, String memberPw) throws Exception {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
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

    public void signUp(MemberDTO memberDTO) throws Exception {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();

            boolean result = memberDAO.saveMember(connection, memberDTO.getMemberId(), memberDTO.getMemberPw(), memberDTO.getMemberName());
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

            MemberEntity member = memberDAO.findMemberById(connection, memberId);
            if (member == null) {
                throw new Exception("존재하지 않는 아이디입니다.");
            }

            MemberRank rank = rankPolicy.getRank(connection, member);
            if (rank == null) {
                throw new Exception("등급 조회에 실패했습니다.");
            }

            MemberDTO memberDTO = new MemberDTO(member);
            memberDTO.setRank(rank);

            return memberDTO;

        } finally {
            JdbcUtil.close(connection);
        }
    }

    public void update(String memberId, String memberPw, String memberName) throws Exception {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();

            MemberEntity member = memberDAO.findMemberById(connection, memberId);
            if (member == null) {
                throw new Exception("존재하지 않는 회원입니다.");
            }
            member.setMemberPw(memberPw);
            member.setMemberName(memberName);

            boolean result = memberDAO.updateMember(connection, member);
            if (!result) {
                throw new Exception("회원 수정에 실패했습니다.");
            }
            JdbcUtil.commit(connection);

        } catch (Exception e) {
            JdbcUtil.rollback(connection);
            throw e;
        } finally {
            JdbcUtil.close(connection);
        }
    }
}
