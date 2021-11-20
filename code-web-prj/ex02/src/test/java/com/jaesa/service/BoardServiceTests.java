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
//		service.getList().forEach(board -> log.info(board));
		service.getList();
	}
	
	@Test
	public void testRegister() {
		BoardVO vo = new BoardVO();
		
		vo.setTitle("01 Title 테스트");
		vo.setContent("01 Content 테스트");
		vo.setWriter("01 Writer 테스트");
		
		
		log.info("----------------------");
		log.info("regist >> " + service.register(vo));
		log.info("----------------------");
	}
	
	@Test
	public void testRegisterKey() {
		BoardVO vo = new BoardVO();
		
		vo.setTitle("02 Key Title 테스트");
		vo.setContent("02 Key Content 테스트");
		vo.setWriter("02 Key Writer 테스트");
		
		long bno = service.register(vo);
		
		log.info("----------------------");
		log.info("regist bno >> " + bno);
		log.info("----------------------");
	}
	
	@Test
	public void testGet() {
		log.info("------------------------");
		log.info("read() >> " + service.get(25L));
		log.info("------------------------");
	}
	
	@Test
	public void testRemove() {
		log.info("------------------------");
		log.info("delete() >> count = " + service.remove(25L));
		log.info("------------------------");
	}
	
	@Test
	public void testModify() {
		BoardVO vo = new BoardVO();
		
		vo.setBno(24L);
		vo.setTitle("ServiceImplModify Title");
		vo.setContent("ServiceImplModify Content");
		vo.setWriter("ServiceImplModify Writer");
		
		log.info("------------------------");
		log.info("update() >> count = " + service.modify(vo));
		log.info("------------------------");
	}
}
