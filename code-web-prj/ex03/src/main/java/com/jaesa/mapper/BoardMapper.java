package com.jaesa.mapper;

import java.util.List;
import java.util.Map;

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
	// 페이지 페이징 처리
	List<BoardVO> getListWithPaging(Criteria cri);
	
	// 전체 페이지 목록  + 검색처리를 위함
	int getTotalCount(Criteria cri);
	
	// 검색 처리
	List<BoardVO> searchTest(Map<String, Map<String, String>> map);
}
