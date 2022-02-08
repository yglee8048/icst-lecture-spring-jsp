package com.lgcns.icst.lecture.springjsp.lec1.biz;

import com.lgcns.icst.lecture.springjsp.lec1.dao.FreeBoardDAO;
import com.lgcns.icst.lecture.springjsp.lec1.dao.MemberDAO;
import com.lgcns.icst.lecture.springjsp.lec1.dto.FreeBoardDTO;
import com.lgcns.icst.lecture.springjsp.lec1.entity.FreeBoardEntity;
import com.lgcns.icst.lecture.springjsp.lec1.entity.MemberEntity;
import com.lgcns.icst.lecture.springjsp.lec1.util.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Service
public class FreeBoardBiz {

    private final MemberDAO memberDAO;
    private final FreeBoardDAO freeBoardDAO;
    private final PointPolicy pointPolicy;

    @Autowired
    public FreeBoardBiz(MemberDAO memberDAO, FreeBoardDAO freeBoardDAO, PointPolicy pointPolicy) {
        this.memberDAO = memberDAO;
        this.freeBoardDAO = freeBoardDAO;
        this.pointPolicy = pointPolicy;
    }

    public List<FreeBoardDTO> findAll() throws Exception {
        List<FreeBoardDTO> list = new ArrayList<>();
        FreeBoardDAO freeBoardDAO = new FreeBoardDAO();
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            List<FreeBoardEntity> freeBoardEntities = freeBoardDAO.findAllFreeBoards(connection);
            for (FreeBoardEntity freeBoardEntity : freeBoardEntities) {
                MemberEntity member = memberDAO.findMemberById(connection, freeBoardEntity.getWriterId());
                if (member == null) {
                    throw new Exception("존재하지 않는 회원 아이디입니다.");
                }
                FreeBoardDTO freeBoardDTO = new FreeBoardDTO(freeBoardEntity, member.getMemberName());
                list.add(freeBoardDTO);
            }
            return list;
        } finally {
            JdbcUtil.close(connection);
        }
    }

    public void save(String content, String writerId) throws Exception {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            boolean result = freeBoardDAO.insertFreeBoard(connection, content, writerId);
            if (result) {

                int point = pointPolicy.getPoint(content);
                MemberEntity member = memberDAO.findMemberById(connection, writerId);
                if (member == null) {
                    throw new Exception("존재하지 않는 회원입니다: " + writerId);
                }
                member.addPoint(point);
                boolean updateResult = memberDAO.updateMember(connection, member);
                if (updateResult) {
                    JdbcUtil.commit(connection);
                } else {
                    throw new Exception("포인트 수정에 실패했습니다.");
                }

            } else {
                throw new Exception("게시글 작성을 실패했습니다.");
            }
        } catch (Exception e) {
            JdbcUtil.rollback(connection);
            throw e;
        } finally {
            JdbcUtil.close(connection);
        }
    }

    public FreeBoardDTO findFreeBoardById(Long id) throws Exception {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            FreeBoardEntity freeBoardEntity = freeBoardDAO.findFreeBoardById(connection, id);
            if (freeBoardEntity == null) {
                throw new Exception("존재하지 않는 게시글입니다.");
            }
            MemberEntity member = memberDAO.findMemberById(connection, freeBoardEntity.getWriterId());
            if (member == null) {
                throw new Exception("존재하지 않는 회원 아이디입니다.");
            }
            return new FreeBoardDTO(freeBoardEntity, member.getMemberName());
        } finally {
            JdbcUtil.close(connection);
        }
    }

    public void update(Long id, String content) throws Exception {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            boolean result = freeBoardDAO.updateFreeBoard(connection, id, content);
            if (result) {
                JdbcUtil.commit(connection);
            } else {
                throw new Exception("게시글이 변경되지 않았습니다.");
            }
        } catch (Exception e) {
            JdbcUtil.rollback(connection);
            throw e;
        } finally {
            JdbcUtil.close(connection);
        }
    }

    public void delete(Long id) throws Exception {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            boolean result = freeBoardDAO.deleteFreeBoardById(connection, id);
            if (result) {
                JdbcUtil.commit(connection);
            } else {
                throw new Exception("게시글 삭제를 실패했습니다.");
            }
        } catch (Exception e) {
            JdbcUtil.rollback(connection);
            throw e;
        } finally {
            JdbcUtil.close(connection);
        }
    }
}
