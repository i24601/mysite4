package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	/* = UserService userService = new UserService(); */
	@Autowired
	private UserService userService;

	@RequestMapping("/joinForm")
	public String joinForm() {
		System.out.println("UserController:joinForm()");
		return "user/joinForm";
	}

	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController:join()");
		System.out.println(userVo.toString());
		System.out.println(userService.join(userVo) + "개 처리완료");
		return "user/joinOk";
	}

	@RequestMapping("/loginForm")
	public String loginForm() {
		System.out.println("UserController:loginForm()");
		return "user/loginForm";
	}

	@RequestMapping("/login")
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {

		System.out.println("UserController:login()");
		System.out.println(userVo.toString());

		UserVo authUser = userService.login(userVo);

		if (authUser != null) {
			System.out.println("UserController:login 성공");
			System.out.println(authUser.toString());
			session.setAttribute("authUser", authUser);
			return "redirect:/main/index";
		} else {
			System.out.println("UserController:login 실패(authUser값 NULL)");
			return "redirect:/user/loginForm?result=fail";
		}

	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("UserController:logout");
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/main/index";
	}

	@RequestMapping("/modifyForm")
	public String modifyForm(HttpSession session, Model model) {
		System.out.println("UserController:modifyForm");
		UserVo userVo = (UserVo) session.getAttribute("authUser");
		int no = userVo.getNo();
		System.out.println("no : " + no);

		userVo = userService.getUser(no);
		System.out.println(userVo.toString());
		model.addAttribute("userData", userVo);
		return "user/modifyForm";
	}

	@RequestMapping("/modify")
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController:modify");
		userVo.setNo(((UserVo) session.getAttribute("authUser")).getNo());
		System.out.println(userVo.toString());
		System.out.println(userService.modifyUser(userVo) + "건 처리되었습니다");

		((UserVo) session.getAttribute("authUser")).setName(userVo.getName());
//		지금은 name만 변경하면 되므로(session에 no, name만 들어가는데 no는 고정)
//		authUser 주소값에 접근해 name만 변경
//		하지만 session에 변경할 값이 많을경우 덮어쓰기도 좋다
//		uVo=userDao.selectUserSession;
//		session.setAttribute("authUser", uVo);
		return "redirect:/main/index";
	}
	
	//ajax용
	@ResponseBody
	@RequestMapping("/idcheck")
	public boolean idcheck(@RequestParam ("userId") String id) {
		boolean chk;
		System.out.println(id);
		UserVo uVo = userService.checkId(id);
		if(uVo==null) {
			chk=true;
		} else {
			chk=false;
		}
		return chk;
	}
}
