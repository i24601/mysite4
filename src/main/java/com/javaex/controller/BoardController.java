package com.javaex.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	//0이나 null이면 문제발생하므로 param 받는경우 디폴트설정
	public String list(@RequestParam("page") int page,
					   @RequestParam( value = "str", required=false, defaultValue="") String str,
					   Model model){
		
		// 페이지당 포스트 숫자 입력
		int postNumber = 10;
		
		System.out.println("BoardController:list()");
		System.out.println(page);
		System.out.println(str);
		
		Map<String, Object> pageInfo = new HashMap<>();
		pageInfo.put("str", str);
		pageInfo.put("page", page);
		pageInfo.put("postNumber", postNumber);
		
		Map<String, Object> pagingResult = boardService.getList(pageInfo);
		
		model.addAttribute("pagingResult", pagingResult);
		return "board/list";
	}
	
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		return "board/writeForm";
	}
	
	@RequestMapping("/write")
	public String write(@ModelAttribute BoardVo boardVo, 
						HttpSession session) {
		boardVo.setUser_no(((UserVo)session.getAttribute("authUser")).getNo());
		System.out.println("BoardController:write");
		System.out.println(boardVo.toString());
		boardService.boardWrite(boardVo);
		System.out.println("글을 썼고 그것은 : "+boardVo.toString());
		return "redirect:/board/list?page=1";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("no") int no,
						 @RequestParam("page")int page,
						 @RequestParam("str")String str) {
		System.out.println("BoardController:delete()");
		boardService.delete(no);
		return "redirect:/board/list?page="+page+"&str="+str;
	}
	
	@RequestMapping("/read")
	public String read(@RequestParam("no") int no, Model model) {
		BoardVo boardVo = boardService.readUser(no,"read");
		model.addAttribute("bVo", boardVo);
		return "board/read";

	}
	
	@RequestMapping("/modifyForm")
	public String modifyForm(@RequestParam("no") int no,
							 @RequestParam("page") int page,
							 Model model) {
		BoardVo boardVo = boardService.readUser(no,"modify");
		model.addAttribute("bVo", boardVo);
		return "board/modifyForm";

	}
	
	@RequestMapping("/modify")
	public String modify(@ModelAttribute BoardVo boardVo, @RequestParam("page")int page, @RequestParam("str")String str, Model model) throws UnsupportedEncodingException {
		System.out.println("modify tostring");
		System.out.println(boardVo.toString());
		boardService.modify(boardVo);
		System.out.println("modify str = "+str);
		String testuri = "redirect:/board/list?page="+page+"&str="+str;
		System.out.println("testuri " + testuri);
		//redirect시 한글깨짐 방지
		String encodedParam = URLEncoder.encode(str, "UTF-8");
		//redirect시 한글깨짐 방지 END
		return "redirect:/board/list?page="+page+"&str="+encodedParam;
	}
	
}