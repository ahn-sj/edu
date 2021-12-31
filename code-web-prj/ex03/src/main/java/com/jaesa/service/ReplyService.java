package com.jaesa.service;

import java.util.List;

import com.jaesa.domain.Criteria;
import com.jaesa.domain.ReplyVO;

public interface ReplyService {
	
	// 등록
	public int register(ReplyVO vo);
	
	// 조회
	public ReplyVO get(Long rno);
	
	// 수정
	public int modify(ReplyVO vo);
	
	// 삭제
	public int remove(Long rno);
	
	// 특정 게시물 댓글 가져오기 
	public List<ReplyVO> getList(Criteria cri, Long bno);
}
