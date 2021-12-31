package com.jaesa.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jaesa.domain.Criteria;
import com.jaesa.domain.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	@Autowired
	private ReplyMapper mapper;
	
	// existed TBL_BOARD.BNO
	private Long[] bnoArr = { 40L, 38L, 37L, 36L, 35L };
	
	@Test
	public void testMapper() {
		log.info("----------------------");
		log.info(mapper);
		log.info("----------------------");
	}
	
	@Test
	public void testCreate() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			ReplyVO vo = new ReplyVO();
			
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("댓글 테스트 " + i);
			vo.setReplyer("replyer" + i);
			
			int rst = mapper.insert(vo);
			log.info("----------------------");
			log.info(rst);
			log.info("----------------------");
		});
	}
	
	@Test
	public void testRead() {
		Long targetRno = 5L;

		ReplyVO vo = mapper.read(targetRno);
		
		log.info("----------------------");
		log.info(vo);
		log.info("----------------------");
	}
	
	@Test
	public void testDelete() {
		Long targetRno = 1L;
		
		int rst = mapper.delete(targetRno);
		
		log.info("----------------------");
		log.info(rst);
		log.info("----------------------");
	}
	
	@Test
	public void testUpdate() {
		Long targetRno = 10L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		vo.setReply("Update reply");
		
		int rst = mapper.update(vo);
		
		log.info("----------------------");
		log.info(rst);
		log.info("----------------------");
	}
	
	@Test
	public void testGetListWithPaging() {
		Criteria cri = new Criteria();
		
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[1]);
		log.info("----------------------");
		replies.forEach(reply -> log.info(reply));
		log.info("----------------------");
	}
	
}
