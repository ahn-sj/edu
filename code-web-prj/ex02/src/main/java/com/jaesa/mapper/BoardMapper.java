package com.jaesa.mapper;

import java.util.List;

import com.jaesa.domain.BoardVO;
import com.jaesa.domain.Criteria;

public interface BoardMapper {
	// select문
	List<BoardVO> getList();
	
	// insert문
	void insert(BoardVO board);
	
	// insert문 + selectKey 
	void insertSelectKey(BoardVO board);
	
	// pk값으로 데이터 조회
	BoardVO read(Long bno);
	
	// pk값으로 데이터 삭제
	int delete(Long bno);
	
	// update문
	int update(BoardVO board);
	
	
	/* DTO */
	List<BoardVO> getListWithPaging(Criteria cri);
}
