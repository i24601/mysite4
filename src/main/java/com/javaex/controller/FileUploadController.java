package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileUploadService;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping("/form")
	public String form() {
		System.out.println("Controller:form");
		return "fileupload/form";
	}
	
	
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file, Model model) {
		System.out.println("Controller:upload");
		String saveName = fileUploadService.restore(file);
		
		System.out.println("saveName:");
		System.out.println(saveName);
		
		model.addAttribute("saveName",saveName);
		return "fileupload/result";
		
	}
}
