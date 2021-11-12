package com.jaesa.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jaesa.domain.BoardVO;

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
}
