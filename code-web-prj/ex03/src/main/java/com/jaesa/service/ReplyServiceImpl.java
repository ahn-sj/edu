package com.jaesa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaesa.domain.Criteria;
import com.jaesa.domain.ReplyVO;
import com.jaesa.mapper.ReplyMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper mapper;
	
	@Override
	public int register(ReplyVO vo) {
		log.info("Service register " + vo);
		
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		log.info("Service get " + rno);
		
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		log.info("Service modify " + vo);
		
		return mapper.update(vo);
	}

	@Override
	public int remove(Long rno) {
		log.info("Service remove " + rno);
		
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("Service getList " + bno);
		
		return mapper.getListWithPaging(cri, bno);
	}

}
