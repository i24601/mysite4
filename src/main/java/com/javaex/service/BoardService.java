package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	public int boardWrite(BoardVo boardVo) {
		System.out.println("BoardService:write");
		
		//댓글쓸때
		if(boardVo.getNo()!=0) {
			System.out.println("댓글쓰기");
			System.out.println(boardVo.toString());
			
			int no = boardVo.getGroup_no();
			boardVo.setGroup_no(no);
			
			Map<String, Integer> orderData = new HashMap<>();
			orderData.put("group_no", boardVo.getGroup_no());
			orderData.put("order_no", boardVo.getOrder_no());
			System.out.println("map값"+orderData.toString());
			
			boardDao.updateOrderNo(orderData);
			
			int newDepth = boardVo.getDepth()+1;
			boardVo.setDepth(newDepth);
			
		}
		else {
		boardVo.setOrder_no(1);
		boardVo.setDepth(0);
		}
		
		System.out.println(boardVo.toString());
		return boardDao.insert(boardVo);

	}

	public int delete(int no) {
		System.out.println("BoardService:delete()");
		
		
		BoardVo bVo = boardDao.selectBoardVo(no);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("no", no);
		map.put("depth", bVo.getDepth());
		map.put("group_no", bVo.getGroup_no());
		map.put("order_no", bVo.getOrder_no());
		int result = boardDao.deleteByMap(map);
		if(result==1) {
			boardDao.updateOrderNo2(map);
		}
		return result;
	}

	public BoardVo readUser(int no, String str) {
		if ("read".equals(str)) {
			boardDao.updateHit(no);
			return boardDao.selectBoardVo(no);
		} else if ("modify".equals(str)) {
			return boardDao.selectBoardVo(no);
		} else {
			System.out.println("BoardService:readUser 오류/nread나 modify 입력되지 않음 ");
			return boardDao.selectBoardVo(0);
		}
	}

	public int modify(BoardVo boardVo) {
		return boardDao.updateBoardVo(boardVo);
	}

	public Map<String, Object> getList(Map<String, Object> pageInfo) {

		String str = (String) pageInfo.get("str");
		int page = (int) pageInfo.get("page");
		int postNumber = (int) pageInfo.get("postNumber");
		int count;
		int firstPage;
		int lastPage;
		int prevPage;
		int nextPage;

		Map<String, Object> paging = new HashMap<>();
		paging.put("str", str);
		paging.put("betweenStart", 1 + postNumber * (page - 1));
		paging.put("betweenEnd", postNumber * page);

		List<BoardVo> bList = boardDao.selectListByMap(paging);
		System.out.println(bList.size());
		System.out.println(bList.toString());
		
		//count는 전체 게시글 갯수
		//초기에 글이 하나도 없는경우 default값으로 1을 줌
		if (bList.size() == 0 || bList == null) {
			count = 1;
		} else {
			count = bList.get(0).getCount();
		}

		// 블록1 = 1~10페이지, 블록2 = 11~20페이지
		int currentBlock = (int) Math.ceil(((float) page) / 10);

		// 전체 page가 10개 이상
		if (count / postNumber >= 10) {
			firstPage = 1 + 10 * (currentBlock - 1);
			// 현재 블록이 마지막 블록이 아님
			if (currentBlock * postNumber * 10 <= count) {
				lastPage = currentBlock * 10;

				if (currentBlock == 1) {
					prevPage = 1;
				} else {
					prevPage = 10 * (currentBlock - 1);
				}

				nextPage = currentBlock * 10 + 1;
			}

			// 현재 블록이 마지막 블록
			else {
				lastPage = (int) ((currentBlock - 1) * 10
						+ Math.ceil(((float) (count - postNumber * 10 * (currentBlock - 1))) / postNumber));
				prevPage = 10 * (currentBlock - 1);
				nextPage = lastPage;
			}

		}

		// 전체페이지가 10개이하
		else {
			firstPage = 1;
			lastPage = (int) Math.ceil(((float) count) / postNumber);
			prevPage = 1;
			nextPage = lastPage;
		}
		
		Map<String, Object> pagingResult = new HashMap<>();
		pagingResult.put("bList", bList);
		pagingResult.put("firstPage", firstPage);
		pagingResult.put("lastPage", lastPage);
		pagingResult.put("prevPage", prevPage);
		pagingResult.put("nextPage", nextPage);
		return pagingResult;

	}

}
