package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("Gallery");
		System.out.println("Controller:list");
		List<GalleryVo> gList = galleryService.getList();
		System.out.println(gList.toString());
		model.addAttribute("gList", gList);
		return "gallery/list";
	}
	
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file,
			 			 @RequestParam("content") String content,
			 			HttpSession session) {
		
		System.out.println("Controller:upload");
		
		int user_no = ((UserVo)session.getAttribute("authUser")).getNo();
		
		Map<String, Object> gMap = new HashMap<String, Object>();
		gMap.put("file", file);
		gMap.put("content", content);
		gMap.put("user_no", user_no);

		int j = galleryService.restore(gMap);
		System.out.println(j+"건 처리");
		return "redirect:/gallery/list";
	}
}
