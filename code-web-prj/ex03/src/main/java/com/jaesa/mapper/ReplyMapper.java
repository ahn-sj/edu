package com.jaesa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jaesa.domain.Criteria;
import com.jaesa.domain.ReplyVO;

public interface ReplyMapper {

	// 외래키를 사용하여 댓글 등록
	public int insert(ReplyVO vo);
	
	// 특정 댓글 읽기
	public ReplyVO read(Long rno);
	
	// 댓글 제거
	public int delete(Long rno);
	
	// 댓글 수정
	public int update(ReplyVO reply);
	
	// 페이징 처리 (댓글)
	public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri,
											@Param("bno") Long bno);
	
}
