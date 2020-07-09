package com.javaex.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	public List<BoardVo> getList(Map<String, Object> paging){
	System.out.println("BoardService:getList()");
	
	return boardDao.selectListByMap(paging);
	}
	
	public int boardWrite(BoardVo boardVo) {
		System.out.println(boardVo.toString());
		return boardDao.insert(boardVo);
		
	}
	
	public int delete(int no) {
		System.out.println("BoardService:delete()");
		
		return boardDao.deleteByNo(no);
	}
	
	public BoardVo readUser(int no) {
		boardDao.updateHit(no);
		return boardDao.selectBoardVo(no);
	}
	
	public int modify(BoardVo boardVo) {
		return boardDao.updateBoardVo(boardVo);
	}
	
}
