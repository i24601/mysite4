package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value="/api/guestbook")
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping(value="/list")
	public List<GuestbookVo> list() {
		System.out.println("Api:list");
		List<GuestbookVo> list = guestbookService.getList();
		System.out.println(list.toString());
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value="/write")
	//json은 데이터(문자열)를 바디에 추가해서 보낸다
	public GuestbookVo write(@RequestBody GuestbookVo gVo) {
		System.out.println("Api:write");
		GuestbookVo guestbookVo = guestbookService.addGuest(gVo);
		return guestbookVo;
	}
	
	@ResponseBody
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public int delete(@ModelAttribute GuestbookVo gVo) {
		System.out.println("apiController:delete");
		System.out.println(gVo.toString());
		
		
		int count = guestbookService.delete(gVo);
		return count;
	}
	
}
