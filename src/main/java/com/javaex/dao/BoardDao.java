package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> selectListByMap(Map<String, Object> paging){
		System.out.println("BoardDao:selectListByMap");
		return sqlSession.selectList("board.selectListByMap", paging);
	}
	
	public int insert(BoardVo boardVo) {
		return sqlSession.insert("board.insert", boardVo);
	}
	
	public int deleteByNo(int no) {
		return sqlSession.delete("board.delete", no);
	}
	
	public int updateHit(int no) {
		return sqlSession.update("board.updateHit", no);
	}
	
	public BoardVo selectBoardVo(int no) {
		return sqlSession.selectOne("board.selectBoardVo", no);
	}
	
	public int updateBoardVo(BoardVo boardVo) {
		return sqlSession.update("board.updateBoardVo", boardVo);
	}
	
}
