package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertGallery(GalleryVo gVo) {
		return sqlSession.insert("gallery.insert", gVo);
	}
	
	public List<GalleryVo> selectList(){
		List<GalleryVo> gList = sqlSession.selectList("gallery.selectGalleryVo");
		gList.toString();
		return gList;
	}
	
	public GalleryVo selectOne(int no) {
		return sqlSession.selectOne("gallery.selectOne", no);
	}
	
	public int deleteByNo(int no) {
		return sqlSession.delete("gallery.deleteByNo", no);
	}
}
