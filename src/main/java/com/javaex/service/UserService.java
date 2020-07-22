package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

//UserController에서 Autowired 사용하기위해 Service 어노텐션 적용
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	// 회원가입
	public int join(UserVo userVo) {
		System.out.println("userService:join");
		return userDao.userInsert(userVo);
	}

	// 로그인
	public UserVo login(UserVo userVo) {
		System.out.println("userService:login");
		UserVo authUser = userDao.selectUserSession(userVo);
		return authUser;
	}

	// 회원정보
	public UserVo getUser(int no) {
		System.out.println("userService:getUser");
		UserVo authUser = userDao.selectUserModify(no);
		return authUser;
	}

	// 회원정보 업데이트
	public int modifyUser(UserVo userVo) {
		System.out.println("userService:modifyUser");
		return userDao.userUpdate(userVo);
	}
	
	public UserVo checkId(String id) {
		UserVo userVo = userDao.selectUser(id);
		return userVo;
	}
}
