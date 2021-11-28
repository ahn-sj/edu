package com.jaesa.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jaesa.domain.BoardVO;
import com.jaesa.domain.Criteria;
import com.jaesa.domain.PageDTO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testGetList() {
		log.info("------------------------------");
		log.info("testGetList");
		log.info("boardMapper >> " + boardMapper);
		log.info("boardMapper.getList() >> " + boardMapper.getList());
		log.info("------------------------------");
	}
	
	@Test
	public void testInsert() {
		BoardVO vo = new BoardVO();
		
		vo.setTitle("Title 테스트");
		vo.setContent("Content 테스트");
		vo.setWriter("Writer 테스트");
		
		boardMapper.insert(vo);
		
		log.info("---------------------");
		log.info("after insert >> " + vo.getBno()); // null
		log.info("---------------------");
	}
	
	@Test
	public void testInsertSelectKey() {
		BoardVO vo = new BoardVO();
		
		vo.setTitle("Key Title 테스트");
		vo.setContent("Key Content 테스트");
		vo.setWriter("Key Writer 테스트");
		
		boardMapper.insertSelectKey(vo);
		
		log.info("---------------------");
		log.info("after insertSelectKey >> " + vo.getBno()); // bno값을 load
		log.info("---------------------");
	}
	
	@Test
	public void testRead() {
		BoardVO vo = boardMapper.read(5L);
		log.info("---------------------");
		log.info(vo);
		log.info("---------------------");
	}
	
	@Test
	public void testDelete() {
		int count = boardMapper.delete(41L);
		log.info("---------------------");
		log.info(count);
		log.info("---------------------");
	}
	
	@Test
	public void testUpdate() {
		BoardVO vo = new BoardVO();
		vo.setBno(21L);
		
		vo.setTitle("Update Title");
		vo.setContent("Update Content");
		vo.setWriter("Update Writer");
		
		log.info("---------------------");
		log.info(boardMapper.update(vo));
		log.info("---------------------");
	}
	
	@Test
	public void testGetListWithPaging() {
		// 1 / 10
		Criteria cri = new Criteria();
		
		List<BoardVO> list = boardMapper.getListWithPaging(cri);
		
		log.info("---------------------");
//		log.info(list);
		list.forEach(b -> log.info(b));
		log.info("---------------------");
	}
	
	@Test
	public void testPageDTO() {
		Criteria cri = new Criteria();
		// 현재 11페이지로 설정
		// case 1 = 아무런 조건 없음
		
		// case 2 = 현재 11페이지 (next, prev true여야 함)
		// cri.setPageNum(11);
		
		// case 3 = 현재 21페이지 (next false여야 함)
		// cri.setPageNum(21);
		
		// case 1~3, cri는 1, 10이고 total(전체 게시물 목록)은 250개로 지정
		// PageDTO pageDTO = new PageDTO(cri, 250);
		
		// ---------------------------------------
		
		// case 4 = 현재 25페이지 (next false여야 함)
		cri.setPageNum(25);
		
		// case 4, total이 251개
		PageDTO pageDTO = new PageDTO(cri, 251);

		
		log.info("---------------------");
		log.info(pageDTO);
		log.info("---------------------");
	}
	
	@Test
	public void testGetTotalCount() {
		Criteria cri = new Criteria();
		
		int cnt = boardMapper.getTotalCount(cri);
		
		log.info("---------------------");
		log.info(cnt);
		log.info("---------------------");
		
	}
	
	@Test
	public void testSearchTest() {
		Map<String, String> map = new HashMap<>();
		
//		T == title, C == content, W = writer
		// 아래 put 3문장을 주석처리하면 
		// select * from tbl_board where rownum < 10
		// 의 결과가 출력된다.
		map.put("T", "TTT");
		map.put("C", "CCC");
		map.put("W", "WWW");
		
		Map<String, Map<String, String>> outer = new HashMap<>();
		outer.put("map", map);
		
		List<BoardVO> list = boardMapper.searchTest(outer);
		
		log.info("---------------------");
		log.info(list);
		log.info("---------------------");
	}
	
	
	@Test
	public void testGetListWithPagingSearch() {
		Criteria cri = new Criteria();
		cri.setType("TCW");
		cri.setKeyword("Test");
		
		List<BoardVO> list = boardMapper.getListWithPaging(cri);
		
		log.info("---------------------");
		list.forEach(b -> log.info(b));
		log.info("---------------------");
	}
}
