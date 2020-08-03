package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping(value="/api/gallery")
public class ApiGalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@ResponseBody
	@RequestMapping(value="/read")
	public GalleryVo read(@RequestParam int no) {
		System.out.println("받은데이터"+no);
		return galleryService.readByNo(no);
	}
	
	@ResponseBody
	@RequestMapping(value="/delete")
	public int delete(@RequestParam int no) {
		System.out.println("받은데이터"+no);
		return galleryService.deleteByNo(no);
	}
	
}
