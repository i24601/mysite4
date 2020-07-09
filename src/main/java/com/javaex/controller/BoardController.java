package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
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
	public String list(@RequestParam("page") int page,
					   @RequestParam( value = "str", required=false, defaultValue="") String str,
					   Model model){
		
		System.out.println("BoardController:list()");
		System.out.println(page);
		System.out.println(str);
		
		//블록1 = 1~10페이지, 블록2 = 11~20페이지
		int currentBlock=(int)Math.ceil(((float)page)/10);
		
		//페이지당 포스트 숫자
		int postNumber = 3;
		
		Map<String, Object> paging = new HashMap<>();
		paging.put("str", str);
		paging.put("betweenStart", 1+postNumber*(page-1));
		paging.put("betweenEnd", postNumber*page);
		
		System.out.println(paging.toString());
		
		List<BoardVo> bList = boardService.getList(paging);
		System.out.println("bList=");
		System.out.println(bList.toString());
		int count = bList.get(0).getCount();
		int firstPage;
		int lastPage;
		int prevPage;
		int nextPage;
		
		//전체 page가 10개 이상
		if(count/postNumber>=10) {
			firstPage = 1+10*(currentBlock-1);
			//현재 블록이 마지막 블록이 아님
			if(currentBlock*postNumber*10<=count) {
				lastPage = currentBlock*10;
				
				
				if(currentBlock==1) {
					prevPage=1;
				}
				else {
					prevPage=10*(currentBlock-1);							
				}
				
				nextPage=currentBlock*10+1;				
			}
			
			//현재 블록이 마지막 블록
			else {
				lastPage = (int)((currentBlock-1)*10+Math.ceil(((float)(count-postNumber*10*(currentBlock-1)))/postNumber));
				prevPage=10*(currentBlock-1);
				nextPage=lastPage;
			}
		}
		
		//전체페이지가 10개이하
		else{
			firstPage = 1;
			lastPage = (int)Math.ceil(((float)count)/postNumber);
			prevPage=1;	
			nextPage=lastPage;
		}
		
		Map<String, Object> pagingResult = new HashMap<>();
		pagingResult.put("firstPage", firstPage);
		pagingResult.put("lastPage", lastPage);
		pagingResult.put("prevPage", prevPage);
		pagingResult.put("nextPage", nextPage);
		
		System.out.println(pagingResult.toString());
		
		model.addAttribute("bList", bList);
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
		System.out.println(boardVo.toString());
		boardService.boardWrite(boardVo);
		return "redirect:/board/list?page=1";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("no") int no,
						 @RequestParam("page")int page) {
		System.out.println("BoardController:delete()");
		boardService.delete(no);
		return "redirect:/board/list?page="+page;
	}
	
	@RequestMapping("/read")
	public String read(@RequestParam("no") int no, Model model) {
		BoardVo boardVo = boardService.readUser(no);
		model.addAttribute("bVo", boardVo);
		return "board/read";

	}
	
	@RequestMapping("/modifyForm")
	public String modifyForm(@RequestParam("no") int no,
							 @RequestParam("page") int page,
							 Model model) {
		BoardVo boardVo = boardService.readUser(no);
		model.addAttribute("bVo", boardVo);
		return "board/modifyForm";

	}
	
	@RequestMapping("/modify")
	public String modify(@ModelAttribute BoardVo boardVo, @RequestParam("page")int page, Model model) {
		System.out.println(boardVo.toString());
		boardService.modify(boardVo);
		return "redirect:/board/list?page="+page;
	}
	
}