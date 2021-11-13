package com.jaesa.service;

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
public class BoardServiceTests {
	
	@Autowired
	private BoardService service;
	
	@Test
	public void testPrint() {
		log.info("----------------------");
		log.info(service);
		log.info("----------------------");
	}
	
	@Test
	public void testGetList() {
		service.getList().forEach(board -> log.info(board));
	}
	
	@Test
	public void getRegister() {
		BoardVO vo = new BoardVO();
		
		vo.setTitle("Key Title 테스트");
		vo.setContent("Key Content 테스트");
		vo.setWriter("Key Writer 테스트");
		
		long bno = service.register(vo);
		
		log.info("----------------------");
		log.info("bno >> " + bno);
		log.info("----------------------");
	}
}
