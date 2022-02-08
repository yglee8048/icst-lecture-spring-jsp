package com.lgcns.icst.lecture.springjsp.lec1.dao;

import com.lgcns.icst.lecture.springjsp.lec1.entity.FreeBoardEntity;
import com.lgcns.icst.lecture.springjsp.lec1.util.JdbcUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FreeBoardDAO {

    public List<FreeBoardEntity> findAllFreeBoards(Connection connection) throws SQLException {
        List<FreeBoardEntity> result = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 쿼리
            String sql = "SELECT ID, CONTENT, WRITER_ID, WRITE_DATE FROM FREE_BOARD ORDER BY ID DESC";
            // Statement 생성
            preparedStatement = connection.prepareStatement(sql);

            // 쿼리 수행
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String content = resultSet.getString("CONTENT");
                String writerId = resultSet.getString("WRITER_ID");
                Date writeDate = resultSet.getDate("WRITE_DATE");

                result.add(new FreeBoardEntity(id, content, writerId, writeDate));
            }
            return result;

        } finally {
            // 생성한 역순으로 반환(close)한다.
            JdbcUtil.close(resultSet);
            JdbcUtil.close(preparedStatement);
        }
    }

    public boolean insertFreeBoard(Connection connection, String content, String writerId) throws SQLException {

        PreparedStatement preparedStatement = null;
        try {
            // 쿼리
            String sql = "INSERT INTO FREE_BOARD VALUES ((SELECT NVL(MAX(ID) + 1, 1) FROM FREE_BOARD), ?, ?, SYSDATE)";
            // Statement 생성
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, content);
            preparedStatement.setString(2, writerId);

            // 쿼리 수행
            int result = preparedStatement.executeUpdate();
            System.out.println("result = " + result);
            return result == 1;
        } finally {
            // 생성한 역순으로 반환(close)한다.
            JdbcUtil.close(preparedStatement);
        }
    }

    public FreeBoardEntity findFreeBoardById(Connection connection, Long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 쿼리
            String sql = "SELECT CONTENT, WRITER_ID, WRITE_DATE FROM FREE_BOARD WHERE ID = ?";
            // Statement 생성
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            // 쿼리 수행
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String content = resultSet.getString("CONTENT");
                String writerId = resultSet.getString("WRITER_ID");
                Date writeDate = resultSet.getDate("WRITE_DATE");

                return new FreeBoardEntity(id, content, writerId, writeDate);
            }
            return null;

        } finally {
            // 생성한 역순으로 반환(close)한다.
            JdbcUtil.close(resultSet);
            JdbcUtil.close(preparedStatement);
        }
    }

    public boolean updateFreeBoard(Connection connection, Long id, String content) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String sql = "UPDATE FREE_BOARD SET CONTENT = ?, WRITE_DATE = SYSDATE WHERE ID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, content);
            preparedStatement.setLong(2, id);

            int result = preparedStatement.executeUpdate();
            return result == 1;

        } finally {
            JdbcUtil.close(preparedStatement);
        }
    }

    public boolean deleteFreeBoardById(Connection connection, Long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String sql = "DELETE FROM FREE_BOARD WHERE ID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            int result = preparedStatement.executeUpdate();
            return result == 1;

        } finally {
            JdbcUtil.close(preparedStatement);
        }
    }
}
