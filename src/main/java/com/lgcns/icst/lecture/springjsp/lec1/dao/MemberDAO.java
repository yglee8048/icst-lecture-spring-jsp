package com.lgcns.icst.lecture.springjsp.lec1.dao;

import com.lgcns.icst.lecture.springjsp.lec1.entity.MemberEntity;
import com.lgcns.icst.lecture.springjsp.lec1.util.JdbcUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MemberDAO {

    public MemberEntity findMemberById(Connection connection, String memberId) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT MEMBER_PW, MEMBER_NAME, POINT FROM MEMBER WHERE MEMBER_ID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String memberPw = resultSet.getString("MEMBER_PW");
                String memberName = resultSet.getString("MEMBER_NAME");
                int point = resultSet.getInt("POINT");
                return new MemberEntity(memberId, memberPw, memberName, point);
            }
        } finally {
            // 생성한 역순으로 반환(close)한다.
            JdbcUtil.close(resultSet);
            JdbcUtil.close(preparedStatement);
        }
        return null;
    }

    public boolean saveMember(Connection connection, String memberId, String memberPw, String memberName) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String sql = "INSERT INTO MEMBER(MEMBER_ID, MEMBER_PW, MEMBER_NAME, POINT) VALUES(?, ?, ?, 0)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberId);
            preparedStatement.setString(2, memberPw);
            preparedStatement.setString(3, memberName);

            int result = preparedStatement.executeUpdate();
            return result == 1;
        } finally {
            JdbcUtil.close(preparedStatement);
        }
    }

    public boolean updateMember(Connection connection, MemberEntity memberEntity) throws Exception {
        PreparedStatement preparedStatement = null;
        try {
            String sql = "UPDATE MEMBER SET MEMBER_PW = ?, MEMBER_NAME = ?, POINT = ? WHERE MEMBER_ID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberEntity.getMemberPw());
            preparedStatement.setString(2, memberEntity.getMemberName());
            preparedStatement.setInt(3, memberEntity.getPoint());
            preparedStatement.setString(4, memberEntity.getMemberId());

            int result = preparedStatement.executeUpdate();
            return result == 1;
        } finally {
            JdbcUtil.close(preparedStatement);
        }
    }

    public Double getPercentRankByMemberId(Connection connection, String memberId) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "select m.MEMBER_ID, rank.pr " +
                    "from MEMBER m " +
                    "left join ( " +
                    "    select MEMBER_ID, " +
                    "           POINT, " +
                    "           percent_rank() over (order by POINT) as pr " +
                    "    from MEMBER " +
                    ") rank " +
                    "on m.MEMBER_ID = rank.MEMBER_ID " +
                    "where m.MEMBER_ID = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("pr");
            }
            return null;
        } finally {
            JdbcUtil.close(resultSet);
            JdbcUtil.close(preparedStatement);
        }
    }
}
