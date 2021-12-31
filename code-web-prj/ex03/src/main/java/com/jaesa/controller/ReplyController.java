package com.jaesa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jaesa.domain.Criteria;
import com.jaesa.domain.ReplyVO;
import com.jaesa.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/replies/*")
@Log4j
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	// 댓글 등록과 테스트
	@PostMapping(value = "/new",							// URL 경로
				consumes = "application/json", 				// 브라우저 -> 서버 전송 시 처리할 데이터 타입
				produces = { MediaType.TEXT_PLAIN_VALUE }) 	// 서버 -> 브라우저 전송 시 반환할 데이터 타입
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		int insertCnt = service.register(vo);
		
		log.info("----------------------");
		log.info("inserCnt >> " + insertCnt);
		log.info("ReplyVO >> " + vo);
		log.info("----------------------");
		
		ResponseEntity<String> rst = null;
		
		if(insertCnt == 1) rst = new ResponseEntity<>("success", HttpStatus.OK);	// 200 OK
		else new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	// 500 Internal Server Error
		
		return rst;
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	
	// 특정 게시물의 댓글 목록 확인
	@GetMapping(value = "/pages/{bno}/{page}",
				produces = {
				    MediaType.APPLICATION_XML_VALUE,
				    MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<List<ReplyVO>> getList(@PathVariable("page") int page,
												 @PathVariable("bno") Long bno) {
		ResponseEntity<List<ReplyVO>> rst = null;
		
		Criteria cri = new Criteria(page, 10);
		
		log.info("----------------------");
		log.info("ReplyController getList");
		log.info("cri >> " + cri);
		log.info("----------------------");
		
		rst = new ResponseEntity<>(service.getList(cri, bno), HttpStatus.OK);
		
		return rst;		
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	
	// 특정 댓글 조회(read)
	@GetMapping(value = "/{rno}",
			produces = {
			    MediaType.APPLICATION_XML_VALUE,
			    MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
		ResponseEntity<ReplyVO> rst = null;
		
		rst = new ResponseEntity<>(service.get(rno), HttpStatus.OK);
		
		log.info("----------------------");
		log.info("ReplyController get " + rno);
		log.info("----------------------");
		
		return rst;
	}
			
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	
	// 댓글 삭제
	@DeleteMapping(value = "/{rno}",
			produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
		ResponseEntity<String> rst = null;
		
		int deleteCnt = service.remove(rno);
		
		log.info("----------------------");
		log.info("ReplyController remove " + rno);
		log.info("deleteCnt >> " + deleteCnt);
		log.info("----------------------");

		if(deleteCnt == 1) rst = new ResponseEntity<>("success", HttpStatus.OK);
		else rst = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		return rst;
	}
	
	/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
	
	// 댓글 수정
	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH },
			value = "/{rno}",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo,
										 @PathVariable("rno") Long rno) {
		ResponseEntity<String> rst = null;
		
		vo.setRno(rno);
		
		int updateCnt = service.modify(vo);
		
		log.info("----------------------");
		log.info("ReplyController modify " + rno);
		log.info("deleteCnt >> " + updateCnt);
		log.info("----------------------");
		
		if(updateCnt == 1) rst = new ResponseEntity<>("success", HttpStatus.OK);
		else rst = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		return rst;
	}
}
