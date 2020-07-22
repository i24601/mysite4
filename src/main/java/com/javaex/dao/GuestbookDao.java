package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;

	// GuestbookVo List로 모두 가져오기
	public List<GuestbookVo> selectAll() {
		System.out.println("userDao:selectAll()");
		return sqlSession.selectList("guestbook.selectAll");
	}
	
	//게스트북 추가
	public int insert(GuestbookVo guestbookVo) {
		System.out.println("userDao:insert()");
		return sqlSession.insert("guestbook.insert", guestbookVo);
	}
	
	//게스트북 삭제 (no)
	public int deleteByGuestbookVo(GuestbookVo guestbookVo) {
		System.out.println("userDao:deleteByGuestbookVo()");
		return sqlSession.delete("guestbook.delete", guestbookVo);
	}
	
	//ajax용 글 등록
	public void insertSelectKey(GuestbookVo guestbookVo) {
		sqlSession.insert("guestbook.insertSelectKey", guestbookVo);
	}
	
	public GuestbookVo selectByNo(int no) {
		GuestbookVo gVo = sqlSession.selectOne("guestbook.selectByNo",no);
		System.out.println("no확인");
		System.out.println(gVo.toString());
		return gVo;
	}
}
