package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value="/guestbook" )
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("GuestbookController:list");
		model.addAttribute("gList", guestbookService.getList());
		return "guestbook/addList";
	}
	
	@RequestMapping("/write")
	public String list(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("GuestbookController:write");
		System.out.println(guestbookVo.toString());
		guestbookService.write(guestbookVo);
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping("/deleteForm")
	public String deleteForm(@RequestParam("no") int no) {
		System.out.println("GuestbookController:deleteForm");
		System.out.println("deleteForm : no = "+no);
		return "guestbook/deleteForm";
	}
	
	@RequestMapping("/delete")
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("GuestbookController:delete");
		System.out.println(guestbookVo.toString());
		guestbookService.delete(guestbookVo);
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping("/ajaxList")
	public String ajaxList(Model model) {
		return "guestbook/ajaxList";
	}
}
