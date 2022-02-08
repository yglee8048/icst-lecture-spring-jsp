package com.lgcns.icst.lecture.springjsp.lec1.biz;

import com.lgcns.icst.lecture.springjsp.lec1.entity.MemberEntity;
import com.lgcns.icst.lecture.springjsp.lec1.entity.MemberRank;

import java.sql.Connection;

public interface RankPolicy {

    MemberRank getRank(Connection connection, MemberEntity member) throws Exception;
}
