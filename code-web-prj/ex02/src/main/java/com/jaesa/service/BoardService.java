package com.jaesa.service;

import java.util.List;

import com.jaesa.domain.BoardVO;
import com.jaesa.domain.Criteria;

public interface BoardService {
	// 등록
	// 1) insert
//	void register(BoardVO board);
	
	// 2) insert - selecyKey
	Long register(BoardVO board);
	
	// 조회
	BoardVO get(Long bno);
	
	// 수정
	int modify(BoardVO board);
	
	// 삭제
	int remove(Long bno);
	
	// 전체 목록 조회
	List<BoardVO> getList();
	
//	DTO
	List<BoardVO> getList(Criteria cri);
	
	int getTotal(Criteria cri);
}
