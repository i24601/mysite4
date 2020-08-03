package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Service
public class GalleryService {

	@Autowired
	GalleryDao gDao;

	public int restore(Map<String, Object> gMap) {
		// 파일카피(hdd에 카피. 서버에 저장하는것은 낭비)
		// 파일 필요정보(no,orgName,saveName,filePath,fileSize) DB에 저장

		////////////////// 데이터 추출//////////////////
		MultipartFile file = (MultipartFile) gMap.get("file");
		int user_no = (Integer) gMap.get("user_no");
		String content = (String) gMap.get("content");

		//String saveDir = "C:\\javaStudy\\gallery\\";
		String saveDir = "/upload";
		// 원파일이름
		String orgName = file.getOriginalFilename();
		System.out.println(orgName);
		// 확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println(exName);
		// 저장파일이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println(saveName);
		// hdd 저장경로
		/* String filePath = saveDir +"\\"+ saveName; */
		String filePath = saveDir +"/"+ saveName;
		System.out.println(filePath);
		// 파일사이즈
		long fileSize = file.getSize();
		System.out.println(fileSize);

		////////////////// 파일 서버에 복사//////////////////
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

		////////////////// 파일정보 DB에 저장//////////////////
		GalleryVo gVo = new GalleryVo(0, user_no, fileSize, content, filePath, orgName, saveName, "");
		System.out.println(gVo.toString());
		
		return gDao.insertGallery(gVo);
	}
	
	public List<GalleryVo> getList() {
		return gDao.selectList();
	}
	
	public GalleryVo readByNo(int no) {
		return gDao.selectOne(no);
	}
	
	public int deleteByNo(int no) {
		return gDao.deleteByNo(no);
	}
}
