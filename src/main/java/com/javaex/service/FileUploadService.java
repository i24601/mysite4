package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	
	
	public String restore(MultipartFile file) {
	//파일카피(hdd에 카피. 서버에 저장하는것은 낭비)
	//파일 필요정보(no,orgName,saveName,filePath,fileSize) DB에 저장
		
		//////////////////데이터 추출//////////////////
		String saveDir = "C:\\javaStudy\\upload\\";
	
		//원파일이름
		String orgName = file.getOriginalFilename();
		System.out.println(orgName);
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println(exName);
		//저장파일이름
		String saveName=System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
		System.out.println(saveName);
		//hdd 저장경로
		String filePath = saveDir+saveName;
		System.out.println(filePath);
		//파일사이즈
		long size = file.getSize();
		System.out.println(size);
		
		//////////////////파일 서버에 복사//////////////////
		try {
				
			byte[] filedata = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			bout.write(filedata);
			bout.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return saveName;
	}
}
