package com.lgcns.icst.lecture.springjsp.lec1.dao;

import com.lgcns.icst.lecture.springjsp.lec1.entity.MemberEntity;
import com.lgcns.icst.lecture.springjsp.lec1.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
