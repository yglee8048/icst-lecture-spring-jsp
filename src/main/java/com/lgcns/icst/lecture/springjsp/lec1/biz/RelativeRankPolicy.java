package com.lgcns.icst.lecture.springjsp.lec1.biz;

import com.lgcns.icst.lecture.springjsp.lec1.dao.MemberDAO;
import com.lgcns.icst.lecture.springjsp.lec1.entity.MemberEntity;
import com.lgcns.icst.lecture.springjsp.lec1.entity.MemberRank;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Primary
@Service
public class RelativeRankPolicy implements RankPolicy {

    private final MemberDAO memberDAO;

    public RelativeRankPolicy(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Override
    public MemberRank getRank(Connection connection, MemberEntity member) throws Exception {
        Double percentRank = memberDAO.getPercentRankByMemberId(connection, member.getMemberId());
        if (percentRank == null) {
            throw new Exception("존재하지 않는 회원입니다.");
        }

        if (percentRank > 0.7) {
            return MemberRank.GOLD;
        } else if (percentRank > 0.3) {
            return MemberRank.SILVER;
        } else {
            return MemberRank.BRONZE;
        }
    }
}
