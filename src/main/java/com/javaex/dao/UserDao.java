package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	// 회원가입
	public int userInsert(UserVo userVo) {
		System.out.println("userDao:userInsert");
		return sqlSession.insert("user.insert", userVo);
	}

	// session정보
	public UserVo selectUserSession(UserVo userVo) {
		System.out.println("userDao:selectUserSession");
		return sqlSession.selectOne("user.selectUserSession", userVo);
	}

	// modifyForm 정보
	public UserVo selectUserModify(int no) {
		System.out.println("userDao:selectUserModify");
		return sqlSession.selectOne("user.selectUserModify", no);
	}

	// 회원정보 수정
	public int userUpdate(UserVo userVo) {
		System.out.println("userDao:userUpdate");
		return sqlSession.update("user.userUpdate", userVo);
	}
	
	//아이디체크
	public UserVo selectUser(String id) {
		System.out.println("userDao:selectUseraaa");
		System.out.println(id);
		return sqlSession.selectOne("user.selectById", id);
	}
	
}
